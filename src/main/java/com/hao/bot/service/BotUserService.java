package com.hao.bot.service;    

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.model.BotUserModel;
import com.gsst.eaf.core.model.PagingInfo;
import java.util.Collection;
import java.util.List;

public interface BotUserService extends BaseService {

    BotUserModel get(java.lang.Integer id);
    
    List<BotUserModel> findByExample(BotUserModel example,PagingInfo pagingInfo);
    
    void delete(BotUserModel model);
    
    void deleteById(java.lang.Integer id);
    
    BotUserModel save(BotUserModel model);
    
    Collection<BotUserModel> saveAll(Collection<BotUserModel> models);
    
}