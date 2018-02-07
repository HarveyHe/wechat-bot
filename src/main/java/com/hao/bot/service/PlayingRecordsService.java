package com.hao.bot.service;    

import java.util.Collection;
import java.util.List;

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.PlayingRecordsModel;

/**
 * @author Harvey.He
 *
 */
public interface PlayingRecordsService extends BaseService {

	/**
	 * 
	 * @param id
	 * @return
	 */
    PlayingRecordsModel get(java.lang.Integer id);
    
    /**
     * 
     * @param model
     * @return
     */
    PlayingRecordsModel save(PlayingRecordsModel model);
    
    /**
     * 
     * @param models
     * @return
     */
    Collection<PlayingRecordsModel> saveAll(Collection<PlayingRecordsModel> models);
 
    /**
     * 
     * @param entity
     * @return
     */
    List<PlayingRecordsModel> query(QueryRequestEntity entity);
    
}