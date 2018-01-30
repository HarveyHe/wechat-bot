package com.hao.bot.service.impl;    

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.service.BotOrderService;
import com.hao.bot.model.BotOrderModel;
import com.gsst.eaf.core.model.PagingInfo;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

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
}