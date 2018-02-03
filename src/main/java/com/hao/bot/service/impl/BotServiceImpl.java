package com.hao.bot.service.impl;    

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.model.BotExtractModel;
import com.hao.bot.model.BotIntegralModel;
import com.hao.bot.model.BotRechargeModel;
import com.hao.bot.service.BotExtractService;
import com.hao.bot.service.BotIntegralService;
import com.hao.bot.service.BotRechargeService;
import com.hao.bot.service.BotService;

@Service
public class BotServiceImpl extends BaseServiceImpl
              implements BotService {
	
	@Autowired
	private BotIntegralService botIntegralService;
	@Autowired
	private BotRechargeService botRechargeService;
	@Autowired
	private BotExtractService botExtractService;
	
	@Override
	public Boolean auditExtrat(Integer id, Integer status) {
		BotExtractModel model = botExtractService.get(id);
		if(1==status){
			//审核通过情况，增加积分
			BotIntegralModel  botIntegralModel = botIntegralService.getByToUserId(model.getToUserName());
			double points = model.getPoints();
			points =  botIntegralModel.getRemainingPoints() - points;
			botIntegralModel.setRemainingPoints(points);
			botIntegralService.save(botIntegralModel);
		}
		model.setStatus(status);
		botExtractService.save(model);
		return true;
	}

	@Override
	public Boolean auditRecharge(Integer id, Integer status) {
		BotRechargeModel model = botRechargeService.get(id);
		if(1==status){
			//审核通过情况，增加积分
			BotIntegralModel  botIntegralModel = botIntegralService.getByToUserId(model.getToUserName());
			double points = model.getPoints();
			if(botIntegralModel == null){
				botIntegralModel = new BotIntegralModel();
				botIntegralModel.setToUserName(model.getToUserName());
				botIntegralModel.setUserName(model.getUserName());
			}else{
				points = points + botIntegralModel.getRemainingPoints();
			}
			botIntegralModel.setRemainingPoints(points);
			botIntegralService.save(botIntegralModel);
		}
		model.setStatus(status);
		botRechargeService.save(model);
		return true;
	}
     
	
}