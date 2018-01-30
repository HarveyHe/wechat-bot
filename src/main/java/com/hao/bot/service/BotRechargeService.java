package com.hao.bot.service;    

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.model.BotRechargeModel;
import com.gsst.eaf.core.model.PagingInfo;
import java.util.Collection;
import java.util.List;

public interface BotRechargeService extends BaseService {

    BotRechargeModel get(java.lang.Integer id);
    
    List<BotRechargeModel> findByExample(BotRechargeModel example,PagingInfo pagingInfo);
    
    void delete(BotRechargeModel model);
    
    void deleteById(java.lang.Integer id);
    
    BotRechargeModel save(BotRechargeModel model);
    
    Collection<BotRechargeModel> saveAll(Collection<BotRechargeModel> models);
    
}