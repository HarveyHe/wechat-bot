package com.hao.bot.demo;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gsst.eaf.core.utils.JSON;
import com.hao.bot.service.impl.BotWechatApiServiceImpl;
import com.hao.bot.thread.MsgHandlerThread;
import com.hao.bot.utils.PrivateObjectUtils;

import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.WeChatApi;
import io.github.biezhi.wechat.api.WeChatApiImpl;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.constant.Constant;
import io.github.biezhi.wechat.api.enums.AccountType;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.Account;
import io.github.biezhi.wechat.api.model.Invoke;
import io.github.biezhi.wechat.api.model.Member;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.DateUtils;

public class MyWechatBot extends WeChatBot {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	
    public MyWechatBot(Config config) {
        super(config);
    }
    
    @Bind(msgType = MsgType.TEXT)
    public void handleText(WeChatMessage message) {
        if (StringUtils.isNotEmpty(message.getName()) && message.getName().equals("重要的测试群")) {
        	String content = message.getRaw().getContent();
        	String[] peopleContent = content.split(":<br/>");
        	List<Account> groupList = super.api().getGroupList();
        	Member member = this.getAccountByGroudList(groupList,message.getFromUserName(),peopleContent[0]);
            log.info("接收到 [{}] 的消息: {}", message.getName(), message.getText());
            log.info("1----------------{}",JSON.serialize(message));
            if(member != null){
            	
            	this.sendMsg(message.getFromUserName(), member.getNickName() + "\n自动回复: test" );
            	URL fileUrl = MyWechatBot.class.getResource("/public/image/start.gif");
//            	URL fileUrl = BotWechatApiServiceImpl.class.getResource(filePath);//.getResourceAsStream(filePath);
        		String imgPath = fileUrl.getPath();
            	this.sendImg(message.getFromUserName(), imgPath);
            }
        }
    }
    private Member getAccountByGroudList(List<Account> groupList,String groudUserName,String memberUserName){
    	for (Account account : groupList) {
    		if(account.getUserName().equals(groudUserName)){
    			List<Member> members = account.getMembers();
    			for (Member member : members) {
					if(member.getUserName().equals(memberUserName)){
						return member;
					}
				}
    		}
			System.out.println(account.toString());
		}
    	return null;
    	
    }
    
    /**
     * 启动微信监听
     */
    @SuppressWarnings({ "unused", "unchecked" })
	public void start() {
    	this.setApi(new WeChatApiImpl(this));
        log.info("wechat-bot: {}", Constant.VERSION);
        api().login(this.config().autoLogin());
		Thread msgHandle = new MsgHandlerThread(this);
        msgHandle.setName("message-handle");
        msgHandle.setDaemon(true);
        msgHandle.start();

        this.other();
    }
    
    public static void main(String[] args) {
    	MyWechatBot helloBot = new MyWechatBot(Config.me().autoLogin(true).showTerminal(true));
    	helloBot.start();
//    	helloBot.sendImgName(name, imgPath)
    }
    
}
