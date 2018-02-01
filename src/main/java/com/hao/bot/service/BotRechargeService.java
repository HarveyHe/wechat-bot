package com.hao.bot.service;    

import java.util.Collection;
import java.util.List;

import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotRechargeModel;

public interface BotRechargeService extends BaseService {

    BotRechargeModel get(java.lang.Integer id);
    
    List<BotRechargeModel> findByExample(BotRechargeModel example,PagingInfo pagingInfo);
    
    void delete(BotRechargeModel model);
    
    void deleteById(java.lang.Integer id);
    
    BotRechargeModel save(BotRechargeModel model);
    
    Collection<BotRechargeModel> saveAll(Collection<BotRechargeModel> models);
    
    List<BotRechargeModel> query(QueryRequestEntity entity);
    
}