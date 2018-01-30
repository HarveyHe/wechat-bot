package com.hao.bot.service;    

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.model.BotExtractModel;
import com.gsst.eaf.core.model.PagingInfo;
import java.util.Collection;
import java.util.List;

public interface BotExtractService extends BaseService {

    BotExtractModel get(java.lang.Integer id);
    
    List<BotExtractModel> findByExample(BotExtractModel example,PagingInfo pagingInfo);
    
    void delete(BotExtractModel model);
    
    void deleteById(java.lang.Integer id);
    
    BotExtractModel save(BotExtractModel model);
    
    Collection<BotExtractModel> saveAll(Collection<BotExtractModel> models);
    
}