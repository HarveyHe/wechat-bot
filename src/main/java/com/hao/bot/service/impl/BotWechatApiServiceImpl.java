package com.hao.bot.service.impl;    

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.blade.kit.http.HttpRequest;
import com.blade.kit.json.JSONObject;
import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.service.BotWechatApiService;

import io.github.biezhi.wechat.api.client.BotClient;
import io.github.biezhi.wechat.exception.WeChatException;
import io.github.biezhi.wechat.utils.MD5Checksum;
import io.github.biezhi.wechat.utils.OkHttpUtils;
import io.github.biezhi.wechat.utils.StringUtils;
import io.github.biezhi.wechat.utils.WeChatUtils;
import me.biezhi.wechat.Constant;
import me.biezhi.wechat.model.WechatContact;
import me.biezhi.wechat.model.WechatMeta;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

@Service
public class BotWechatApiServiceImpl extends BaseServiceImpl
              implements BotWechatApiService {
	
	
	@Autowired
	private  BeanFactory beanFactory;
	
	private BotClient client = new BotClient(client(null));;

	@Override
	public void registerSingleton(String beanName, Object singletonObject) {
		//注入
		((ConfigurableListableBeanFactory) beanFactory).registerSingleton(beanName, singletonObject);
		
	}

	@Override
	public boolean sendText(WechatMeta meta,String toUser, String msg) {
		
		String url = meta.getBase_uri() + "/webwxsendmsg?lang=zh_CN&pass_ticket=" + meta.getPass_ticket();
		JSONObject body = new JSONObject();

		String clientMsgId = DateKit.getCurrentUnixTime() + StringKit.getRandomNumber(5);
		JSONObject Msg = new JSONObject();
		Msg.put("Type", 1);
		Msg.put("Content", msg);
		Msg.put("FromUserName", meta.getUser().getString("UserName"));
		Msg.put("ToUserName", toUser);
		Msg.put("LocalID", clientMsgId);
		Msg.put("ClientMsgId", clientMsgId);

		body.put("BaseRequest", meta.getBaseRequest());
		body.put("Msg", Msg);

		HttpRequest request = HttpRequest.post(url).contentType("application/json;charset=utf-8")
				.header("Cookie", meta.getCookie()).send(body.toString());

		log.info("发送消息...");
		log.debug("" + request);
		request.body();
		request.disconnect();
		return true;
	}

	@Override
	public boolean sendImg(WechatMeta meta,String toUser, String filePath) {
		 String url = meta.getBase_uri() + "/webwxsendmsgimg?fun=async&f=json&pass_ticket=" + meta.getPass_ticket();
		 JSONObject body = new JSONObject();
		 
        String mediaId = this.uploadMedia(meta,toUser, filePath);
        if (StringUtils.isEmpty(mediaId)) {
            log.warn("Media为空");
            return false;
        }
		 String clientMsgId = DateKit.getCurrentUnixTime() + StringKit.getRandomNumber(5);
		 JSONObject Msg = new JSONObject();
		 Msg.put("Type", 3);
		 Msg.put("MediaId", mediaId);
		 Msg.put("FromUserName", meta.getUser().getString("UserName"));
		 Msg.put("ToUserName", toUser);
		 Msg.put("LocalID", clientMsgId);
		 Msg.put("ClientMsgId", clientMsgId);
		 
		 body.put("BaseRequest", meta.getBaseRequest());
		 body.put("Msg", Msg);
		 
		 HttpRequest request = HttpRequest.post(url).contentType("application/json;charset=utf-8")
				 .header("Cookie", meta.getCookie()).send(body.toString());
		 
		 log.info("发送消息...");
		 log.debug("" + request);
		 request.body();
		 request.disconnect();
		 return true;
		 
	}
	
	public String uploadMedia(WechatMeta meta,String toUser, String filePath) {
//		File file = new File(filePath);
		URL fileUrl = BotWechatApiServiceImpl.class.getResource(filePath);//.getResourceAsStream(filePath);
		String s = fileUrl.getPath();
        File file = new File(s);
        if (!file.exists()) {
            throw new WeChatException("文件[" + filePath + "]不存在");
        }
        log.info("开始上传文件: {}"+filePath);
        long   size      = file.length();
        String mimeType  = WeChatUtils.getMimeType(filePath);
        String mediatype = "doc";
        if (mediatype.contains("image")) {
            mediatype = "pic";
        }
        if (mediatype.contains("audio")) {
            mediatype = "audio";
        }
        if (mediatype.contains("video")) {
            mediatype = "video";
        }
        String url     = String.format("%s/webwxuploadmedia?f=json", meta.getBase_uri());
        String mediaId = System.currentTimeMillis() / 1000 + StringUtils.random(6);
        
        JSONObject body = new JSONObject();

        Map<String, Object> uploadMediaRequest = new HashMap<>(10);
        uploadMediaRequest.put("UploadType", 2);
        uploadMediaRequest.put("BaseRequest", meta.getBaseRequest());
        uploadMediaRequest.put("ClientMediaId", mediaId);
        uploadMediaRequest.put("TotalLen", size);
        uploadMediaRequest.put("StartPos", 0);
        uploadMediaRequest.put("DataLen", size);
        uploadMediaRequest.put("MediaType", 4);
        uploadMediaRequest.put("FromUserName", meta.getUser().getString("UserName"));
        uploadMediaRequest.put("ToUserName", toUser);
        uploadMediaRequest.put("FileMd5", MD5Checksum.getMD5Checksum(file.getPath()));
        
//        String dataTicket = this.client.cookie("webwx_data_ticket");
        String dataTicket = "";
//        String dataTicket = meta.getCookie();
        String[] cookies = meta.getCookie().split(";");
        for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].startsWith("webwx_data_ticket")){
				dataTicket = cookies[i].split("=")[1];
				break;
			}
		}
        if (StringUtils.isEmpty(dataTicket)) {
            throw new WeChatException("缺少了附件Cookie");
        }
        

//        ApiResponse response = this.client.send(new StringRequest(url).post().multipart()
//                .fileName(file.getName())
//                .add("id", "WU_FILE_0")
//                .add("name", filePath)
//                .add("type", mimeType)
//                .add("lastModifieDate", new SimpleDateFormat("yyyy MM dd HH:mm:ss").format(new Date()))
//                .add("size", String.valueOf(size))
//                .add("mediatype", mediatype)
//                .add("uploadmediarequest", WeChatUtils.toJson(uploadMediaRequest))
//                .add("webwx_data_ticket", dataTicket)
//                .add("pass_ticket", meta.getPass_ticket())
//                .add("filename", okhttp3.RequestBody.create(MediaType.parse(mimeType), file)));
//        body.put("BaseRequest", meta.getBaseRequest());
//		 body.put("Msg", Msg);
		 
//        ApiResponse response = this.client.send(new StringRequest(url).post().multipart()
//        		.fileName(file.getName())
        		body.put("id", "WU_FILE_0");
        		body.put("name", filePath);
        		body.put("type", mimeType);
        		body.put("lastModifieDate", new SimpleDateFormat("yyyy MM dd HH:mm:ss").format(new Date()));
        		body.put("size", String.valueOf(size));
        		body.put("mediatype", mediatype);
        		body.put("uploadmediarequest", WeChatUtils.toJson(uploadMediaRequest));
        		body.put("webwx_data_ticket", dataTicket);
        		body.put("pass_ticket", meta.getPass_ticket());
        		body.put("filename", okhttp3.RequestBody.create(MediaType.parse(mimeType), file));
        	HttpRequest request3 = HttpRequest.post(url).contentType("application/json;charset=utf-8")
       				 .header("Cookie", meta.getCookie()).send(body.toString());
       		 
       		 log.info("发送消息...");
       		 log.debug("" + request3);
       		 request3.body();
       		 request3.disconnect();
        		
//        MediaResponse mediaResponse = response.parse(MediaResponse.class);
//        if (!mediaResponse.success()) {
//            log.warn("上传附件失败: {}" + mediaResponse.getMsg());
//        }
//        log.info("文件上传成功: {}"+ filePath);
        return mediaId;
    }
	
	
	private static OkHttpClient client(Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpUtils.configureToIgnoreCertificate(builder);
        if (interceptor != null) {
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

	@Override
	public JSONObject getGroudAccount(String groudName) {
		WechatContact wechatContact = Constant.CONTACT;
		
		Map<String, JSONObject> groupMap = wechatContact.getGroupMap();
		
		JSONObject contact = null;
		for (Map.Entry<String,JSONObject> entry : groupMap.entrySet()) {
			contact = entry.getValue();
			if(contact.getString("NickName").equals(com.gsst.eaf.core.config.Config.get("hao.bot.groud.name"))){
				return contact;
			}
		}
		return null;
	}
     
	
}