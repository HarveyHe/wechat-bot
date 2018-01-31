package com.hao.bot.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blade.kit.base.Config;
import com.hao.bot.thread.MyThread;

import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.DateUtils;
import me.biezhi.wechat.Constant;
import me.biezhi.wechat.WechatRobot;

@RestController
@RequestMapping("/api")
public class BotController {
	private Logger logger = LoggerFactory.getLogger(BotController.class);
	
	
	@RequestMapping(value = "/start.do",method = { RequestMethod.POST ,RequestMethod.GET})
	public String start(HttpServletRequest request, HttpServletResponse response){
		logger.info("启动");
		try {
			
			Constant.config = Config.load("classpath:config.properties");
			
			WechatRobot wechatRobot = new WechatRobot();
			String url = wechatRobot.getQrCodeUrl();
			MyThread myThread = new com.hao.bot.thread.MyThread(wechatRobot);
			myThread.start();
			return url;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "启动失败";
		}
		
	}
}
