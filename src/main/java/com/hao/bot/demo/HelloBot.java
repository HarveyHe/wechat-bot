package com.hao.bot.demo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gsst.eaf.core.utils.JSON;

import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.AccountType;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;

public class HelloBot extends WeChatBot {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
    public HelloBot(Config config) {
        super(config);
    }
    
    @Bind(msgType = MsgType.TEXT)
    public void handleText(WeChatMessage message) {
        if (StringUtils.isNotEmpty(message.getName())) {
        	
            log.info("接收到 [{}] 的消息: {}", message.getName(), message.getText());
            log.info("1----------------{}",JSON.serialize(message));
//            this.sendMsg(message.getFromUserName(), "自动回复: " + message.getText());
        }
    }
    @Bind(accountType = AccountType.TYPE_GROUP)
    public void groupMessage(WeChatMessage message) {
        if(StringUtils.isNotEmpty(message.getName())){
            log.info("接收到群 [{}] 的消息: {}", message.getName(), message.getText());
            log.info("2----------------{}",JSON.serialize(message));
//            this.sendMsg(message.getFromUserName(), "自动回复: " + message.getText());
        }
    }
    @Bind(msgType = MsgType.TEXT,accountType = AccountType.TYPE_GROUP)
    public void groupMessage2(WeChatMessage message) {
    	if(StringUtils.isNotEmpty(message.getName())){
    		log.info("接收到群 [{}] 的消息: {}", message.getName(), message.getText());
    		log.info("3----------------{}",JSON.serialize(message));
//            this.sendMsg(message.getFromUserName(), "自动回复: " + message.getText());
    	}
    }
    
    public static void main(String[] args) {
    	HelloBot helloBot = new HelloBot(Config.me().autoLogin(true).showTerminal(true));
    	helloBot.start();
//    	helloBot.sendImgName(name, imgPath)
    }
    
}
