package com.hao.bot.service.impl;    

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.service.PlayingRecordsService;
import com.hao.bot.model.PlayingRecordsModel;
import com.gsst.eaf.core.model.PagingInfo;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class PlayingRecordsServiceImpl extends BaseServiceImpl
              implements PlayingRecordsService {
              
   public PlayingRecordsModel get(java.lang.Integer id){
       return this.dao.get(PlayingRecordsModel.class,id);
   }
    
   public List<PlayingRecordsModel> findByExample(PlayingRecordsModel example,PagingInfo pagingInfo){
       if(example == null){
           example = new PlayingRecordsModel();
       }
       return this.dao.findByExample(example,null,pagingInfo);
   }
    
   public void delete(PlayingRecordsModel model){
       this.dao.remove(model);
   }
    
   public void deleteById(java.lang.Integer id){
       this.dao.removeByPk(PlayingRecordsModel.class,id);
   }
    
   public PlayingRecordsModel save(PlayingRecordsModel model){
       return this.dao.save(model);
   }
    
   public Collection<PlayingRecordsModel> saveAll(Collection<PlayingRecordsModel> models){
       return this.dao.saveAll(models);
   }        
}