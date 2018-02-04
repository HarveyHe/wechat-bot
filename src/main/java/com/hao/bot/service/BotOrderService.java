package com.hao.bot.service;    

import java.util.Collection;
import java.util.List;

import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotOrderModel;

public interface BotOrderService extends BaseService {

    BotOrderModel get(java.lang.Integer id);
    
    List<BotOrderModel> findByExample(BotOrderModel example,PagingInfo pagingInfo);
    
    void delete(BotOrderModel model);
    
    void deleteById(java.lang.Integer id);
    
    BotOrderModel save(BotOrderModel model);
    
    Collection<BotOrderModel> saveAll(Collection<BotOrderModel> models);
    
    
    public BotOrderModel getByToUserId(String id,Integer status);
    
    List<BotOrderModel> query(QueryRequestEntity entity);
    
    List<BotOrderModel> queryByStatusAndPayingNo(String payingNo,Integer status);
    
}