package com.hao.bot.service;    

import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.model.PlayingRecordsModel;
import com.gsst.eaf.core.model.PagingInfo;
import java.util.Collection;
import java.util.List;

public interface PlayingRecordsService extends BaseService {

    PlayingRecordsModel get(java.lang.Integer id);
    
    List<PlayingRecordsModel> findByExample(PlayingRecordsModel example,PagingInfo pagingInfo);
    
    void delete(PlayingRecordsModel model);
    
    void deleteById(java.lang.Integer id);
    
    PlayingRecordsModel save(PlayingRecordsModel model);
    
    Collection<PlayingRecordsModel> saveAll(Collection<PlayingRecordsModel> models);
    
}