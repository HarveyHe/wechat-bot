package com.hao.bot.service;    

import java.util.Collection;
import java.util.List;

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotRechargeModel;

/**
 * 
 * @author Harvey.He
 *
 */
public interface BotRechargeService extends BaseService {

	/**
	 * 
	 * @param id
	 * @return
	 */
    BotRechargeModel get(java.lang.Integer id);
    
    /**
     * 
     * @param model
     * @return
     */
    BotRechargeModel save(BotRechargeModel model);
    
    /**
     * 
     * @param models
     * @return
     */
    Collection<BotRechargeModel> saveAll(Collection<BotRechargeModel> models);
    
    /**
     * 
     * @param entity
     * @return
     */
    List<BotRechargeModel> query(QueryRequestEntity entity);
    
    
}