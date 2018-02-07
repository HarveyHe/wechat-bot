package com.hao.bot.service.impl;    

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.model.BotUserModel;
import com.hao.bot.service.BotUserService;
/**
 * 
 * @author Harvey.He
 *
 */
@Service
public class BotUserServiceImpl extends BaseServiceImpl
              implements BotUserService {
	@Override      
	public BotUserModel get(java.lang.Integer id){
		return this.dao.get(BotUserModel.class,id);
	}
	@Override
	public BotUserModel save(BotUserModel model){
		return this.dao.save(model);
	}
	@Override
	public Collection<BotUserModel> saveAll(Collection<BotUserModel> models){
		return this.dao.saveAll(models);
	}        
}