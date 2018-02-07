package com.hao.bot.service;    

import java.util.Collection;
import java.util.List;

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotExtractModel;

/**
 * 
 * @author Harvey.He
 *
 */
public interface BotExtractService extends BaseService {

	/**
	 * 获取提取分记录
	 * @param id
	 * @return
	 */
    BotExtractModel get(java.lang.Integer id);
    
    /**
     * 保存
     * @param model
     * @return
     */
    BotExtractModel save(BotExtractModel model);
    
    /**
     * 
     * @param models
     * @return
     */
    Collection<BotExtractModel> saveAll(Collection<BotExtractModel> models);
    
    /**
     * 
     * @param entity
     * @return
     */
    List<BotExtractModel> query(QueryRequestEntity entity);
    
    
}