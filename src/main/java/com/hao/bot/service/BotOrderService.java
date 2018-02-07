package com.hao.bot.service;    

import java.util.Collection;
import java.util.List;

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotOrderModel;

/**
 * 
 * @author Harvey.He
 *
 */
public interface BotOrderService extends BaseService {

	/**
	 * 
	 * @param id
	 * @return
	 */
    BotOrderModel get(java.lang.Integer id);
    
    /**
     * 
     * @param model
     * @return
     */
    BotOrderModel save(BotOrderModel model);
    
    /**
     * 
     * @param models
     * @return
     */
    Collection<BotOrderModel> saveAll(Collection<BotOrderModel> models);
    
    /**
     * 
     * @param id
     * @param status
     * @return
     */
    public BotOrderModel getByToUserId(String id,Integer status);
    
    /**
     * 
     * @param entity
     * @return
     */
    List<BotOrderModel> query(QueryRequestEntity entity);
    
    /**
     * 
     * @param payingNo
     * @param status
     * @return
     */
    List<BotOrderModel> queryByStatusAndPayingNo(String payingNo,Integer status);
    
}