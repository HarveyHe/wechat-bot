package com.hao.bot.service.impl;    

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

import me.biezhi.wechat.Constant;
import me.biezhi.wechat.model.WechatContact;
import me.biezhi.wechat.model.WechatMeta;
/**
 * 
 * @author Harvey.He
 *
 */
@Service
public class BotWechatApiServiceImpl extends BaseServiceImpl
              implements BotWechatApiService {
	
	
	@Autowired
	private  BeanFactory beanFactory;
	

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
		JSONObject msgParam = new JSONObject();
		msgParam.put("Type", 1);
		msgParam.put("Content", msg);
		msgParam.put("FromUserName", meta.getUser().getString("UserName"));
		msgParam.put("ToUserName", toUser);
		msgParam.put("LocalID", clientMsgId);
		msgParam.put("ClientMsgId", clientMsgId);

		body.put("BaseRequest", meta.getBaseRequest());
		body.put("Msg", msgParam);

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
		 return true;
		 
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