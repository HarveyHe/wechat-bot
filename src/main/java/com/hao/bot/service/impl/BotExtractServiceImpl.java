package com.hao.bot.service.impl;    

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.service.BotExtractService;
import com.hao.bot.model.BotExtractModel;
import com.gsst.eaf.core.model.PagingInfo;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class BotExtractServiceImpl extends BaseServiceImpl
              implements BotExtractService {
              
   public BotExtractModel get(java.lang.Integer id){
       return this.dao.get(BotExtractModel.class,id);
   }
    
   public List<BotExtractModel> findByExample(BotExtractModel example,PagingInfo pagingInfo){
       if(example == null){
           example = new BotExtractModel();
       }
       return this.dao.findByExample(example,null,pagingInfo);
   }
    
   public void delete(BotExtractModel model){
       this.dao.remove(model);
   }
    
   public void deleteById(java.lang.Integer id){
       this.dao.removeByPk(BotExtractModel.class,id);
   }
    
   public BotExtractModel save(BotExtractModel model){
       return this.dao.save(model);
   }
    
   public Collection<BotExtractModel> saveAll(Collection<BotExtractModel> models){
       return this.dao.saveAll(models);
   }        
}