package com.hao.bot.service.impl;    

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.gsst.eaf.core.utils.JSON;
import com.hao.bot.entity.AwardResultEntity;
import com.hao.bot.model.BotExtractModel;
import com.hao.bot.model.BotIntegralModel;
import com.hao.bot.model.BotRechargeModel;
import com.hao.bot.service.BotExtractService;
import com.hao.bot.service.BotIntegralService;
import com.hao.bot.service.BotRechargeService;
import com.hao.bot.service.BotService;

/**
 * @author Harvey.He
 */
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

	@Override
	public AwardResultEntity getCurrentAward() {
		AwardResultEntity resultEntity = null;
		String url = "http://www.dy22.com/pk10/getDatad";
		Response res = null;
		try {
			res = Jsoup.connect(url)
					.header("Accept", "*/*")
					.header("Accept-Encoding", "gzip, deflate")
					.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
					.header("Content-Type", "application/json;charset=UTF-8")
					.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
					.timeout(10000).ignoreContentType(true).execute();
			String body = res.body();
			resultEntity = JSON.deSerialize(AwardResultEntity.class, body);
		} catch (IOException e) {
			log.error(e);
		}
    	return resultEntity;
	}
     
	
}