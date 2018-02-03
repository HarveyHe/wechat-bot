package com.hao.bot.service;    

import com.gsst.eaf.core.service.BaseService;

public interface BotService extends BaseService {

    
	/**
	 * 审核提取分
	 * @param id
	 * @param status
	 * @return
	 */
    Boolean auditExtrat(Integer id,Integer status);
    
    /**
     * 审核充值
     * @param id
     * @param status
     * @return
     */
    Boolean auditRecharge(Integer id, Integer status);
    
}