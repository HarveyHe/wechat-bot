package com.hao.bot.service;    

import java.util.Collection;
import java.util.List;

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotIntegralModel;

/**
 * 
 * @author Harvey.He
 *
 */
public interface BotIntegralService extends BaseService {

	/**
	 * 
	 * @param id
	 * @return
	 */
    BotIntegralModel get(java.lang.Integer id);
    
    /**
     * 
     * @param model
     * @return
     */
    BotIntegralModel save(BotIntegralModel model);
    /**
     * 
     * @param models
     * @return
     */
    Collection<BotIntegralModel> saveAll(Collection<BotIntegralModel> models);
    /**
     * 
     * @param id
     * @return
     */
    BotIntegralModel getByToUserId(String id);
    
    /**
     * 
     * @param entity
     * @return
     */
    List<BotIntegralModel> query(QueryRequestEntity entity);
    
}