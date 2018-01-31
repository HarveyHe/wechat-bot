package com.hao.bot.service.impl;    

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.service.BotIntegralService;
import com.hao.bot.model.BotIntegralModel;
import com.gsst.eaf.core.model.PagingInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Service
public class BotIntegralServiceImpl extends BaseServiceImpl
              implements BotIntegralService {
              
   public BotIntegralModel get(java.lang.Integer id){
       return this.dao.get(BotIntegralModel.class,id);
   }
    
   public List<BotIntegralModel> findByExample(BotIntegralModel example,PagingInfo pagingInfo){
       if(example == null){
           example = new BotIntegralModel();
       }
       return this.dao.findByExample(example,null,pagingInfo);
   }
    
   public void delete(BotIntegralModel model){
       this.dao.remove(model);
   }
    
   public void deleteById(java.lang.Integer id){
       this.dao.removeByPk(BotIntegralModel.class,id);
   }
    
   public BotIntegralModel save(BotIntegralModel model){
       return this.dao.save(model);
   }
    
   public Collection<BotIntegralModel> saveAll(Collection<BotIntegralModel> models){
       return this.dao.saveAll(models);
   }

	@Override
	public BotIntegralModel getByToUserId(String id) {
		BotIntegralModel example = new BotIntegralModel();
		example.setToUserName(id);
		List<BotIntegralModel> list = this.dao.findByExample(example);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}        
}