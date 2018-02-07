package com.hao.bot.service;    

import java.util.Collection;
import java.util.List;

import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.service.BaseService;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.PlayingRecordsModel;

/**
 * @author Harvey.He
 *
 */
public interface PlayingRecordsService extends BaseService {

    PlayingRecordsModel get(java.lang.Integer id);
    
    List<PlayingRecordsModel> findByExample(PlayingRecordsModel example,PagingInfo pagingInfo);
    
    void delete(PlayingRecordsModel model);
    
    void deleteById(java.lang.Integer id);
    
    PlayingRecordsModel save(PlayingRecordsModel model);
    
    Collection<PlayingRecordsModel> saveAll(Collection<PlayingRecordsModel> models);
 
    List<PlayingRecordsModel> query(QueryRequestEntity entity);
    
    /**
     * 获取当天期数
     * @return
     */
    Integer getCurrentDateTotalRecords();
    
    /**
     * 生成一期记录
     * @return
     */
    PlayingRecordsModel genPlayingRecords();
}