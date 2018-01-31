package com.hao.bot.service;    

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.model.BotIntegralModel;
import com.gsst.eaf.core.model.PagingInfo;
import java.util.Collection;
import java.util.List;

public interface BotIntegralService extends BaseService {

    BotIntegralModel get(java.lang.Integer id);
    
    List<BotIntegralModel> findByExample(BotIntegralModel example,PagingInfo pagingInfo);
    
    void delete(BotIntegralModel model);
    
    void deleteById(java.lang.Integer id);
    
    BotIntegralModel save(BotIntegralModel model);
    
    Collection<BotIntegralModel> saveAll(Collection<BotIntegralModel> models);
    
    BotIntegralModel getByToUserId(String id);
    
}