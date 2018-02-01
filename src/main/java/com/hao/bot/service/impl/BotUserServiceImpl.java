package com.hao.bot.service.impl;    

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.service.BotUserService;
import com.hao.bot.model.BotUserModel;
import com.gsst.eaf.core.model.PagingInfo;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class BotUserServiceImpl extends BaseServiceImpl
              implements BotUserService {
	@Override      
	public BotUserModel get(java.lang.Integer id){
		return this.dao.get(BotUserModel.class,id);
	}
	@Override
	public List<BotUserModel> findByExample(BotUserModel example,PagingInfo pagingInfo){
		if(example == null){
			example = new BotUserModel();
		}
		return this.dao.findByExample(example,null,pagingInfo);
	}
	@Override
	public void delete(BotUserModel model){
		this.dao.remove(model);
	}
	@Override
	public void deleteById(java.lang.Integer id){
		this.dao.removeByPk(BotUserModel.class,id);
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