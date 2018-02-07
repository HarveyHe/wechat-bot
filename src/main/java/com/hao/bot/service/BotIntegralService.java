package com.hao.bot.service;    

import java.util.Collection;
import java.util.List;

import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotIntegralModel;

/**
 * 
 * @author Harvey.He
 *
 */
public interface BotIntegralService extends BaseService {

    BotIntegralModel get(java.lang.Integer id);
    
    List<BotIntegralModel> findByExample(BotIntegralModel example,PagingInfo pagingInfo);
    
    void delete(BotIntegralModel model);
    
    void deleteById(java.lang.Integer id);
    
    BotIntegralModel save(BotIntegralModel model);
    
    Collection<BotIntegralModel> saveAll(Collection<BotIntegralModel> models);
    
    BotIntegralModel getByToUserId(String id);
    
    List<BotIntegralModel> query(QueryRequestEntity entity);
    
}