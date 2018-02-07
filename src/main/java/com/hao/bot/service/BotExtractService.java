package com.hao.bot.service;    

import java.util.Collection;
import java.util.List;

import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotExtractModel;

/**
 * 
 * @author Harvey.He
 *
 */
public interface BotExtractService extends BaseService {

    BotExtractModel get(java.lang.Integer id);
    
    List<BotExtractModel> findByExample(BotExtractModel example,PagingInfo pagingInfo);
    
    void delete(BotExtractModel model);
    
    void deleteById(java.lang.Integer id);
    
    BotExtractModel save(BotExtractModel model);
    
    Collection<BotExtractModel> saveAll(Collection<BotExtractModel> models);
    
    List<BotExtractModel> query(QueryRequestEntity entity);
    
    
}