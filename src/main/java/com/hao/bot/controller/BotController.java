package com.hao.bot.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blade.kit.base.Config;
import com.hao.bot.entity.CommonRequest;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.entity.RestResult;
import com.hao.bot.model.BotExtractModel;
import com.hao.bot.model.BotIntegralModel;
import com.hao.bot.model.BotOrderModel;
import com.hao.bot.model.BotRechargeModel;
import com.hao.bot.model.PlayingRecordsModel;
import com.hao.bot.service.BotExtractService;
import com.hao.bot.service.BotIntegralService;
import com.hao.bot.service.BotOrderService;
import com.hao.bot.service.BotRechargeService;
import com.hao.bot.service.PlayingRecordsService;
import com.hao.bot.thread.MyThread;

import me.biezhi.wechat.Constant;
import me.biezhi.wechat.WechatRobot;

@RestController
@RequestMapping("/api")
public class BotController {
	private Logger logger = LoggerFactory.getLogger(BotController.class);
	
	@Autowired
	private BotRechargeService botRechargeService ;
	@Autowired
	private BotExtractService botExtractService ;
	@Autowired
	private BotOrderService botOrderService ;
	@Autowired
	private BotIntegralService botIntegralService ;
	@Autowired
	private PlayingRecordsService playingRecordsService ;
	
	
	
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
			result.setCode(-1);
			result.setMessage("启动失败");
		}
		return result;
	}
	
	@RequestMapping(value = "/query/order.do",method = { RequestMethod.POST ,RequestMethod.GET})
	public RestResult<List<BotOrderModel>> queryOrder(@RequestBody CommonRequest<QueryRequestEntity>  request){
		RestResult<List<BotOrderModel>> result = new RestResult<>();
		
		result.setCode(0);
		result.setData(botOrderService.query(request.getRequest()));
		result.setPagingInfo(request.getRequest().getPagingInfo());
		return result;
	}
	
	@RequestMapping(value = "/query/extract.do",method = { RequestMethod.POST ,RequestMethod.GET})
	public RestResult<List<BotExtractModel>> queryExtract(@RequestBody CommonRequest<QueryRequestEntity>  request){
		RestResult<List<BotExtractModel>> result = new RestResult<>();
		
		result.setCode(0);
		result.setData(botExtractService.query(request.getRequest()));
		result.setPagingInfo(request.getRequest().getPagingInfo());
		return result;
	}
	
	@RequestMapping(value = "/query/integral.do",method = { RequestMethod.POST ,RequestMethod.GET})
	public RestResult<List<BotIntegralModel>> queryIntegral(@RequestBody CommonRequest<QueryRequestEntity>  request){
		RestResult<List<BotIntegralModel>> result = new RestResult<>();
		result.setCode(0);
		result.setData(botIntegralService.query(request.getRequest()));
		result.setPagingInfo(request.getRequest().getPagingInfo());
		return result;
	}
	
	@RequestMapping(value = "/query/rechargel.do",method = { RequestMethod.POST ,RequestMethod.GET}, produces = "application/json")
	public RestResult<List<BotRechargeModel>> queryRechargel(@RequestBody CommonRequest<QueryRequestEntity>  request){
		RestResult<List<BotRechargeModel>> result = new RestResult<>();
		result.setCode(0);
		result.setData(botRechargeService.query(request.getRequest()));
		result.setPagingInfo(request.getRequest().getPagingInfo());
		return result;
	}
	
	@RequestMapping(value = "/query/playing/records.do",method = { RequestMethod.POST ,RequestMethod.GET})
	public RestResult<List<PlayingRecordsModel>> queryPlayingRecords(@RequestBody CommonRequest<QueryRequestEntity>  request){
		RestResult<List<PlayingRecordsModel>> result = new RestResult<>();
		
		result.setCode(0);
		result.setData(playingRecordsService.query(request.getRequest()));
		result.setPagingInfo(request.getRequest().getPagingInfo());
		return result;
	}
	
	@RequestMapping(value = "/audit/extract.do",method = { RequestMethod.POST ,RequestMethod.GET})
	public RestResult<String> auditExtract(@RequestBody CommonRequest<QueryRequestEntity>  request){
		RestResult<String> result = new RestResult<>();
		if(request.getRequest().getId() != null && request.getRequest().getStatus() != null){
			botExtractService.audit(request.getRequest().getId(), request.getRequest().getStatus());
			result.setCode(0);
			result.setData("");
		}else{
			result.setCode(-1);
			result.setData("确认失败");
		}
		return result;
	}
	@RequestMapping(value = "/audit/rechargel.do",method = { RequestMethod.POST ,RequestMethod.GET})
	public RestResult<String> auditRechargel(@RequestBody CommonRequest<QueryRequestEntity>  request){
		RestResult<String> result = new RestResult<>();
		if(request.getRequest().getId() != null && request.getRequest().getStatus() != null){
			botRechargeService.audit(request.getRequest().getId(), request.getRequest().getStatus());
			result.setCode(0);
			result.setData("");
		}else{
			result.setCode(-1);
			result.setData("确认失败");
		}
		return result;
	}
}
