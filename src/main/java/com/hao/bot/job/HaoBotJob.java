package com.hao.bot.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.blade.kit.json.JSONObject;
import com.gsst.eaf.core.context.Context;
import com.hao.bot.constant.HbConstant;
import com.hao.bot.entity.AwardEntity;
import com.hao.bot.entity.AwardResultEntity;
import com.hao.bot.entity.PaylingRecordEntity;
import com.hao.bot.entity.PaylingResultEntity;
import com.hao.bot.model.BotIntegralModel;
import com.hao.bot.model.BotOrderModel;
import com.hao.bot.model.PlayingRecordsModel;
import com.hao.bot.service.BotIntegralService;
import com.hao.bot.service.BotOrderService;
import com.hao.bot.service.BotService;
import com.hao.bot.service.BotWechatApiService;
import com.hao.bot.service.PlayingRecordsService;

import me.biezhi.wechat.Constant;
import me.biezhi.wechat.model.WechatMeta;

public class HaoBotJob {
	
	/**
	 * 定时获取北京赛车网开奖数据
	 */
	public void getPk10Data(){
		BotService botService = Context.getBean(BotService.class);
		PlayingRecordsService playingRecordsService = Context.getBean(PlayingRecordsService.class);
		BotWechatApiService botWechatApiService = Context.getBean(BotWechatApiService.class);
		WechatMeta wechatMeta = Constant.WECHAT_META;
		JSONObject contact = botWechatApiService.getGroudAccount(com.gsst.eaf.core.config.Config.get("hao.bot.groud.name"));
		
		AwardResultEntity entity = botService.getCurrentAward();
		if(entity != null){
			if(HbConstant.currentPaylingRecordId == null){
				//第一次运行
				this.setCurrentPeriod(entity, false);
			}else{
				AwardEntity currentAward = entity.getCurrent();
				String playingNo = currentAward.getPeriodNumber();
				if(!playingNo.equals(HbConstant.currentPaylingNo)){
					//还是当前一期
					Date currentDate = new Date();
					if(HbConstant.canBuy && this.addDate(HbConstant.startTime, 4).getTime() <= currentDate.getTime()){
						HbConstant.endTime = currentDate;
						//停止下注
						botWechatApiService.sendText(wechatMeta, contact.getString("UserName"), 
								"=================\n停止下注\n=================");	
						HbConstant.canBuy = false;
					}
				}else{
					//换期了
					//保存这期记录
					PlayingRecordsModel model =playingRecordsService.get(HbConstant.currentPaylingRecordId);
					String awardNumbers = currentAward.getAwardNumbers();
					String[] keyIndexs = awardNumbers.split(",");
					model.setRecord1(Integer.parseInt(keyIndexs[0]));
					model.setRecord2(Integer.parseInt(keyIndexs[1]));
					model.setRecord3(Integer.parseInt(keyIndexs[2]));
					model.setRecord4(Integer.parseInt(keyIndexs[3]));
					model.setRecord5(Integer.parseInt(keyIndexs[4]));
					model.setRecord6(Integer.parseInt(keyIndexs[5]));
					model.setRecord7(Integer.parseInt(keyIndexs[6]));
					model.setRecord8(Integer.parseInt(keyIndexs[7]));
					model.setRecord9(Integer.parseInt(keyIndexs[8]));
					model.setRecord10(Integer.parseInt(keyIndexs[9]));
					playingRecordsService.save(model);
					//结算。。。。。。。
					this.settleResult();
					
					//开始下注
					this.setCurrentPeriod(entity, true);
				}
			}
			
		}
	}
	
	private void setCurrentPeriod(AwardResultEntity entity,boolean isCanBuy){
		AwardEntity next = entity.getNext();
		AwardEntity current = entity.getCurrent();
		PlayingRecordsService playingRecordsService = Context.getBean(PlayingRecordsService.class);
		String playingNo = next.getPeriodNumber();
		PlayingRecordsModel model1 = new PlayingRecordsModel();
		model1.setPlayingNo(playingNo);
		model1 = playingRecordsService.save(model1);
		HbConstant.currentPaylingRecordId = model1.getPlayingRecordsId();
		HbConstant.currentPaylingNo = playingNo;
		HbConstant.startTime = current.getAwardTime();
		HbConstant.endTime = null;
		
		HbConstant.canBuy = isCanBuy;
		if( isCanBuy ){
			BotWechatApiService botWechatApiService = Context.getBean(BotWechatApiService.class);
			WechatMeta wechatMeta = Constant.WECHAT_META;
			JSONObject contact = botWechatApiService.getGroudAccount(com.gsst.eaf.core.config.Config.get("hao.bot.groud.name"));
			botWechatApiService.sendText(wechatMeta, contact.getString("UserName"), "=================\n开始下注\n=================\n");
		}
	}
	
	
	
	/**
	 * 结算结果
	 *  游戏规则，以大吃小，比如这把总盘买10000.然后这10000抽掉水后，剩下的金额就从大赔付到小，例如最大点那门买了2000就带本能得四千，这样从最大赔到小。比如有一门有五人买，赢了就是先买先得，输了也就是先买先吃。 
	 */
	private void settleResult(){
		BotWechatApiService botWechatApiService = Context.getBean(BotWechatApiService.class);
		WechatMeta wechatMeta = Constant.WECHAT_META;
		JSONObject contact = botWechatApiService.getGroudAccount(com.gsst.eaf.core.config.Config.get("hao.bot.groud.name"));
		
		
		PaylingResultEntity result = this.getPayingResult();
		Map<Integer,PaylingRecordEntity> records = result.getRecords();
		com.hao.bot.service.BotOrderService botOrderService = Context.getBean(BotOrderService.class);
		List<BotOrderModel> orders = botOrderService.queryByStatusAndPayingNo(HbConstant.currentPaylingNo, 0);
		StringBuilder msg = new StringBuilder();
		msg.append("当前期结果：\n");
		msg.append("参考期数：");
		msg.append(result.getReferencePeriods());
		msg.append("\n");
		msg.append(result.getReferenceValue());
		msg.append("\n");
		for (Entry<Integer, PaylingRecordEntity> entry : records.entrySet()) {
			PaylingRecordEntity pe = entry.getValue();
			msg.append("注");
			msg.append(entry.getKey());
			msg.append(":[");
			msg.append(pe.getRecordValue());
			msg.append("=");
			msg.append(pe.getScore());
			msg.append("点]\n");
		}
		botWechatApiService.sendText(wechatMeta, contact.getString("UserName"), msg.toString());	
		msg = new StringBuilder();
		if(!CollectionUtils.isEmpty(orders) ){
			Set<Integer> set = new HashSet<>();
			Double totalPoints = 0.0;
			for (BotOrderModel botOrderModel : orders) {
				set.add(botOrderModel.getRecord());
				totalPoints += botOrderModel.getPoints();
			} 
			BotIntegralService botIntegralService = Context.getBean(BotIntegralService.class);
			
			//获取最大注数
			PaylingRecordEntity maxEntity= null;
			for (Integer rd : set) {
				PaylingRecordEntity entity = records.get(rd);
				if(maxEntity == null){
					maxEntity = entity;
				}else{
					
					if( maxEntity.getScore() < entity.getScore() 
							|| ( maxEntity.getMax() <  entity.getMax() 
									|| (maxEntity.getMax() ==  entity.getMax() 
									&& (maxEntity.getSec() <  entity.getSec() 
											|| (maxEntity.getSec() ==  entity.getSec() 
											&& maxEntity.getMin() <  entity.getMin())
										)
									)
								)
						){
						
						maxEntity = entity;
					}
				}
			}
			msg.append("最大注:\n");
			msg.append("注");
			msg.append(maxEntity.getRecord());
			msg.append(":[");
			msg.append(maxEntity.getRecordValue());
			msg.append("=");
			msg.append(maxEntity.getScore());
			msg.append("]\n");
			msg.append("总下注单数:");
			msg.append(orders.size());
			botWechatApiService.sendText(wechatMeta, contact.getString("UserName"), msg.toString());	
			msg = new StringBuilder();
			msg.append("获胜者：\n");
			Integer maxRecord = maxEntity.getRecord();
			List<BotIntegralModel> saveBotIntegralModel = new ArrayList<>();
			for (BotOrderModel botOrderModel : orders) {
				BotIntegralModel botIntegralModel =botIntegralService.getByToUserId(botOrderModel.getToUserName());
				botOrderModel.setStatus(1);
				if(maxRecord == botOrderModel.getRecord()){
					msg.append("【");
					msg.append(botOrderModel.getUserName());
					msg.append("】获取：");
					Double point = 0.0;
					if(totalPoints > botOrderModel.getPoints()){
						point = botOrderModel.getPoints();
					}else{
						point = totalPoints;
					}
					botIntegralModel.setRemainingPoints(botIntegralModel.getRemainingPoints() + point + botOrderModel.getPoints());
					totalPoints = totalPoints - point;
					msg.append(point);
					msg.append("\n");
					saveBotIntegralModel.add(botIntegralModel);
				}
			} 
			botIntegralService.saveAll(saveBotIntegralModel);
			botOrderService.saveAll(orders);
			botWechatApiService.sendText(wechatMeta, contact.getString("UserName"), msg.toString());	
			msg = new StringBuilder();
			
		}else{
			msg.append("当期没有人下注");
			botWechatApiService.sendText(wechatMeta, contact.getString("UserName"), msg.toString());
		}
		
	}
	
	
	private PaylingResultEntity getPayingResult(){
		com.hao.bot.service.PlayingRecordsService playingRecordsService = Context.getBean(PlayingRecordsService.class);
		PlayingRecordsModel model =playingRecordsService.get(HbConstant.currentPaylingRecordId);
		PaylingResultEntity result = new PaylingResultEntity();
		Map<Integer,PaylingRecordEntity> records = new HashMap<>();
		records.put(1, this.regulation(model, 1, 0, 1, 2));
		records.put(2, this.regulation(model, 2, 1, 2, 3));
		records.put(3, this.regulation(model, 3, 3, 4, 5));
		records.put(4, this.regulation(model, 4, 4, 5, 6));
		records.put(5, this.regulation(model, 5, 5, 6, 7));
		records.put(6, this.regulation(model,6, 6, 7, 8));
		records.put(7, this.regulation(model, 7, 7, 8, 9));
		records.put(8, this.regulation(model, 8, 0, 3, 6));
		records.put(9, this.regulation(model, 9, 1, 4, 7));
		records.put(10, this.regulation(model, 10, 2, 5, 9));
		result.setRecords(records);
		result.setReferencePeriods(model.getPlayingNo());
		result.setReferenceValue(String.format("【%s-%s-%s-%s-%s-%s-%s-%s-%s-%s】", model.getRecord1(), 
				model.getRecord2(), model.getRecord3(), model.getRecord4(),
				model.getRecord5(), model.getRecord6(), model.getRecord7(),
				model.getRecord8(), model.getRecord9(), model.getRecord10()));
		return result;
	}
	/**
	 * 三公玩法，以北京赛车开奖得出结果为准，总有十门，第一门1.2.3道，第二门2.3.4道.第三门4.5.6道，第四门5.6.7道，
	 * 第五门6.7.8道，第六门7.8.9道，第七门8..9.10道，第八门1.4.7道，第九门2.5.8道，第十门3.6.9道，
	 * 比如，1道开3号球2道开2号球三道开4号球，三个道的数字相加得出为9点，就是一门9点。如三道开1号球6道开9号球9道开10号球，三个道的数字相加得出为20点，就是第十门为0点。
	 * 如些以推，0点为最小九点为最大，如有不同门同点的，以其中最大的球号数相比，10号最大1号最小，数字大的为大。  
	 * 
	 * @param playingRecordsModel
	 * @param record 注
	 * @param index1 门道1
	 * @param index2 门道2
	 * @param index3 门道3
	 * @return
	 */
	private PaylingRecordEntity regulation(PlayingRecordsModel model,Integer record,Integer index1,Integer index2,Integer index3){
		Integer[] keyIndexs =  new Integer[]{model.getRecord1(),model.getRecord2(),model.getRecord3(),
				model.getRecord4(),model.getRecord5(),model.getRecord6(),
				model.getRecord7(),model.getRecord8(),model.getRecord9(),model.getRecord10()};
		PaylingRecordEntity result = new PaylingRecordEntity();
		result.setRecord(record);
		result.setRecordValue(String.format("%s.%s.%s", keyIndexs[index1], keyIndexs[index2] ,keyIndexs[index3]));
		int[] newInts = {keyIndexs[index1], keyIndexs[index2] ,keyIndexs[index3]};
		Arrays.sort(newInts);
		result.setMax(newInts[2]);
		result.setSec(newInts[1]);
		result.setMin(newInts[0]);
		int score = (keyIndexs[index1] + keyIndexs[index2] + keyIndexs[index3]) % 10;
		result.setScore(score);
		return result;
	}
	/**
	 * 增加分钟
	 * @param date
	 * @param minute
	 * @return
	 */
	private  Date addDate(Date date,int minute){
		if(date == null){
			date=new Date();//取时间  
		}
		Calendar calendar = new GregorianCalendar();  
		calendar.setTime(date);  
//		calendar.add(Calendar.DATE,day);//把日期往后增加一天.整数往后推,负数往前移动  
		calendar.add(Calendar.MINUTE, minute);
		date=calendar.getTime(); //这个时间就是日期往后推的结果   
		return date;
		
	}

}
