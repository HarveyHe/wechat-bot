package com.hao.bot.service;    

import java.util.Collection;

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.model.BotUserModel;

/**
 * 
 * @author Harvey.He
 *
 */
public interface BotUserService extends BaseService {

	/**
	 * 
	 * @param id
	 * @return
	 */
    BotUserModel get(java.lang.Integer id);
    
    
    /**
     * 
     * @param model
     * @return
     */
    BotUserModel save(BotUserModel model);
    
    /**
     * 
     * @param models
     * @return
     */
    Collection<BotUserModel> saveAll(Collection<BotUserModel> models);
    
}