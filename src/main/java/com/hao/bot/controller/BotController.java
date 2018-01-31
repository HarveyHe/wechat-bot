package com.hao.bot.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blade.kit.base.Config;
import com.hao.bot.entity.RestResult;
import com.hao.bot.thread.MyThread;

import me.biezhi.wechat.Constant;
import me.biezhi.wechat.WechatRobot;

@RestController
@RequestMapping("/api")
public class BotController {
	private Logger logger = LoggerFactory.getLogger(BotController.class);
	
	
	@RequestMapping(value = "/start.do",method = { RequestMethod.POST ,RequestMethod.GET})
	public RestResult<String> start(HttpServletRequest request, HttpServletResponse response){
		logger.info("启动");
		RestResult<String> result = new RestResult<>();
		try {
			
			Constant.config = Config.load("classpath:config.properties");
			
			WechatRobot wechatRobot = new WechatRobot();
			String url = wechatRobot.getQrCodeUrl();
			MyThread myThread = new com.hao.bot.thread.MyThread(wechatRobot);
			myThread.start();
			result.setCode(0);
			result.setData(url);
			
		} catch (Exception e) {
			logger.info("content",e);
			result.setCode(0);
			result.setMessage("启动失败");
		}
		return result;
	}
	
	@RequestMapping(value = "/query/order.do",method = { RequestMethod.POST ,RequestMethod.GET})
	public RestResult<String> queryOrder(HttpServletRequest request, HttpServletResponse response){
		return null;
	}
}
