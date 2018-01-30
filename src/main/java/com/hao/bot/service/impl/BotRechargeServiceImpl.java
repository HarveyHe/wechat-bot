package com.hao.bot.service.impl;    

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.service.BotRechargeService;
import com.hao.bot.model.BotRechargeModel;
import com.gsst.eaf.core.model.PagingInfo;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class BotRechargeServiceImpl extends BaseServiceImpl
              implements BotRechargeService {
              
   public BotRechargeModel get(java.lang.Integer id){
       return this.dao.get(BotRechargeModel.class,id);
   }
    
   public List<BotRechargeModel> findByExample(BotRechargeModel example,PagingInfo pagingInfo){
       if(example == null){
           example = new BotRechargeModel();
       }
       return this.dao.findByExample(example,null,pagingInfo);
   }
    
   public void delete(BotRechargeModel model){
       this.dao.remove(model);
   }
    
   public void deleteById(java.lang.Integer id){
       this.dao.removeByPk(BotRechargeModel.class,id);
   }
    
   public BotRechargeModel save(BotRechargeModel model){
       return this.dao.save(model);
   }
    
   public Collection<BotRechargeModel> saveAll(Collection<BotRechargeModel> models){
       return this.dao.saveAll(models);
   }        
}