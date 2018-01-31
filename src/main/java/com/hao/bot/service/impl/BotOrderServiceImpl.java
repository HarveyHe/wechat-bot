package com.hao.bot.service.impl;    

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.model.BotOrderModel;
import com.hao.bot.service.BotOrderService;

@Service
public class BotOrderServiceImpl extends BaseServiceImpl
              implements BotOrderService {
              
   public BotOrderModel get(java.lang.Integer id){
       return this.dao.get(BotOrderModel.class,id);
   }
    
   public List<BotOrderModel> findByExample(BotOrderModel example,PagingInfo pagingInfo){
       if(example == null){
           example = new BotOrderModel();
       }
       return this.dao.findByExample(example,null,pagingInfo);
   }
    
   public void delete(BotOrderModel model){
       this.dao.remove(model);
   }
    
   public void deleteById(java.lang.Integer id){
       this.dao.removeByPk(BotOrderModel.class,id);
   }
    
   public BotOrderModel save(BotOrderModel model){
       return this.dao.save(model);
   }
    
   public Collection<BotOrderModel> saveAll(Collection<BotOrderModel> models){
       return this.dao.saveAll(models);
   }

	@Override
	public BotOrderModel getByToUserId(String id,Integer status) {
		BotOrderModel example = new BotOrderModel();
		example.setToUserName(id);
		example.setStatus(status);
		List<BotOrderModel> list = this.dao.findByExample(example);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}        
}