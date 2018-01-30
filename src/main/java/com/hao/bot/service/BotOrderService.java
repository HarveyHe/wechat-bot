package com.hao.bot.service;    

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.model.BotOrderModel;
import com.gsst.eaf.core.model.PagingInfo;
import java.util.Collection;
import java.util.List;

public interface BotOrderService extends BaseService {

    BotOrderModel get(java.lang.Integer id);
    
    List<BotOrderModel> findByExample(BotOrderModel example,PagingInfo pagingInfo);
    
    void delete(BotOrderModel model);
    
    void deleteById(java.lang.Integer id);
    
    BotOrderModel save(BotOrderModel model);
    
    Collection<BotOrderModel> saveAll(Collection<BotOrderModel> models);
    
}