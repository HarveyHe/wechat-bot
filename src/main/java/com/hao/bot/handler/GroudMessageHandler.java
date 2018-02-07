package com.hao.bot.handler;

import org.apache.commons.lang3.StringUtils;

import com.gsst.eaf.core.context.Context;
import com.hao.bot.constant.HbConstant;
import com.hao.bot.enumtype.MsgHandlerType;
import com.hao.bot.model.BotExtractModel;
import com.hao.bot.model.BotIntegralModel;
import com.hao.bot.model.BotOrderModel;
import com.hao.bot.model.BotRechargeModel;
import com.hao.bot.service.BotExtractService;
import com.hao.bot.service.BotIntegralService;
import com.hao.bot.service.BotOrderService;
import com.hao.bot.service.BotRechargeService;

/**
 * 
 * @author Harvey.He
 *
 */
public class GroudMessageHandler {

	private String wechatUserId;
	private String name;
	private String message;
	
	private BotRechargeService botRechargeService = Context.getBean(BotRechargeService.class);
	private BotExtractService botExtractService = Context.getBean(BotExtractService.class);
	private BotOrderService botOrderService = Context.getBean(BotOrderService.class);
	private BotIntegralService botIntegralService = Context.getBean(BotIntegralService.class);
	
	
	
	public String getWechatUserId() {
		return wechatUserId;
	}
	public void setWechatUserId(String wechatUserId) {
		this.wechatUserId = wechatUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@SuppressWarnings("unused")
	private GroudMessageHandler() {
		
	}
	public GroudMessageHandler(String wechatUserId,String name,String message ) {
		this.wechatUserId = wechatUserId;
		this.name = name;
		this.message = message;
		
		
	}
	
	/**
	 * 充分，提取分，查分，下单，撤销单
	 * @return
	 */
	public String handler(){
		if(StringUtils.isNotBlank(message)){
			MsgHandlerType msgHandlerType = MsgHandlerType.get(message);
			if(msgHandlerType != null){
				String result = "";
				switch (msgHandlerType) {
				case RECHARGE:
					result = this.recharge();
					break;
				case EXTRACT:
					result = this.extract();
					break;
				case QUERY:
					result = this.query();
					break;
				case ORDER:
					result = this.order();
					
					break;
				case CANCEL:
					result = this.cancel();
					
					break;
				case MSGRULE:
					
					result = this.rule();
					break;

				default:
					break;
				}
				return result;
			}
			
		}
		return null;
	}
	/**
	 * 规则
	 * @return
	 */
	private String rule (){
		StringBuilder result = new StringBuilder();
		result.append("玩法如下：\n");
		result.append("下单格式如下：\n下单4=3000\n");
		result.append("撤销单格式如下：\n撤销单\n");
		result.append("查分格式如下：\n查分\n");
		result.append("充分格式如下：\n充分=3000\n");
		result.append("提取格式如下：\n提取分=100.3\n");
		return result.toString();
	}

	private void atUserName(StringBuilder result){
		result.append("@");
		result.append(name);
		result.append("\n");
	}
	/**
	 * 充值
	 * 格式,充值=1000.2
	 */
	private String recharge(){
		StringBuilder result = new StringBuilder();
		this.atUserName(result);
		String newString = message.substring(3);
		BotRechargeModel model = new BotRechargeModel();
		try {
			Double points = Double.parseDouble(newString);
			if(points > 0){
				
				model.setPoints(points);
				model.setStatus(0);
				model.setToUserName(this.wechatUserId);
				model.setUserName(name);
				botRechargeService.save(model);
				result.append("正在充分,耐心等待审核！");
			}else{
				result.append("充分格式有误；格式如下：\n充分=1000.2,必须大于0");
				
			}
		} catch (NumberFormatException e) {
			result.append("充分格式有误；格式如下：\n充分=1000.2");
		}
		return result.toString();
			
	}
	
	
	/**
	 * 查分
	 */
	private String query(){
		BotIntegralModel model = botIntegralService.getByToUserId(wechatUserId);
		Double points = model == null?0d:model.getRemainingPoints();
		StringBuilder result = new StringBuilder();
		this.atUserName(result);
		result.append("剩余积分为：");
		result.append(points);
		return result.toString();
	}
	
	/**
	 * 下单
	 * 下单2=300
	 * 下单4=3000
	 */
	private String order(){
		StringBuilder result = new StringBuilder();
		this.atUserName(result);
		// 注意开始时间截止时间
		if(HbConstant.canBuy){
			
			try {
				int orderContentSplitSize = 2;
				String newString = message.substring(2);
				String[] orderContent = newString.split("=");
				if(orderContent.length == orderContentSplitSize){
					BotOrderModel model = new BotOrderModel();
					Double points = Double.parseDouble(orderContent[1]);
					Integer record = Integer.parseInt(orderContent[0]);
					if(points != null && record != null){
						
						BotIntegralModel botIntegralModel = botIntegralService.getByToUserId(wechatUserId);
						Double remainingPoints = botIntegralModel == null?0d:botIntegralModel.getRemainingPoints();
						if(remainingPoints < points){
							result.append("剩余分不足，剩余分：" + remainingPoints);
						}else{
							model.setPoints(points);
							model.setStatus(0);
							model.setToUserName(this.wechatUserId);
							model.setUserName(name);
							model.setRecord(record);
							/**
							 * 下注期数
							 */
							model.setPlayingNo(HbConstant.currentPaylingNo);
							botOrderService.save(model);
							if(botIntegralModel != null){
								double newRemainingPoints = remainingPoints - points;
								botIntegralModel.setRemainingPoints(newRemainingPoints);
								botIntegralService.save(botIntegralModel);
							}
							result.append("已下单！下注：");
							result.append(record);
							result.append("  下注金额：");
							result.append(points);
							
						}
					}else{
						result.append("下单格式有误；格式如下：\n下单4=3000");
					}
					
				}else{
					result.append("下单格式有误；格式如下：\n下单4=3000");
					
				}
			} catch (NumberFormatException e) {
				result.append("下单格式有误；格式如下：\n下单4=1000.2");
			}
		}else{
			result.append("现在不是下注时间");
		}
			
		return result.toString();
	}

	/**
	 * 撤销单
	 */
	private String cancel (){
		StringBuilder result = new StringBuilder();
		this.atUserName(result);
		if(HbConstant.canBuy){
			
			BotOrderModel model = botOrderService.getByToUserId(wechatUserId, 0);
			if(model == null){
				result.append("未下单!");
			}else{
				model.setStatus(-1);
				botOrderService.save(model);
				result.append("撤销单！撤销注：");
				result.append(model.getRecord());
				result.append("  下注金额：");
				result.append(model.getPoints());
				BotIntegralModel botIntegralModel = botIntegralService.getByToUserId(wechatUserId);
				if(botIntegralModel!=null){
					Double remainingPoints = botIntegralModel.getRemainingPoints();
					double newRemainingPoints = model.getPoints() + remainingPoints;
					botIntegralModel.setRemainingPoints(newRemainingPoints);
					botIntegralService.save(botIntegralModel);
				}
			}
		}else{
			result.append("现在不是下注时间,不能撤销单");
		}
		return result.toString();
	}
	/**
	 * 提取分
	 * 提取分=100.3
	 */
	private String extract  (){
		StringBuilder result = new StringBuilder();
		this.atUserName(result);
		String newString = message.substring(4);
		try {
			BotExtractModel model = new BotExtractModel();
			Double points = Double.parseDouble(newString);
			model.setPoints(points);
			model.setStatus(0);
			model.setToUserName(this.wechatUserId);
			model.setUserName(name);
			botExtractService.save(model);
			result.append("正在提取分,耐心等待审核！");
		} catch (NumberFormatException e) {
			result.append("提取分格式有误；格式如下：\n提取分=100.3");
		}
		return result.toString();
		
	}
}
