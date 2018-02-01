package com.hao.bot.service.impl;    

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.PlayingRecordsModel;
import com.hao.bot.service.PlayingRecordsService;

@Service
public class PlayingRecordsServiceImpl extends BaseServiceImpl
              implements PlayingRecordsService {
	@Override      
	public PlayingRecordsModel get(java.lang.Integer id){
		return this.dao.get(PlayingRecordsModel.class,id);
	}
	@Override
	public List<PlayingRecordsModel> findByExample(PlayingRecordsModel example,PagingInfo pagingInfo){
		if(example == null){
			example = new PlayingRecordsModel();
		}
		return this.dao.findByExample(example,null,pagingInfo);
	}
	@Override
	public void delete(PlayingRecordsModel model){
		this.dao.remove(model);
	}
	@Override
	public void deleteById(java.lang.Integer id){
		this.dao.removeByPk(PlayingRecordsModel.class,id);
	}
	@Override
	public PlayingRecordsModel save(PlayingRecordsModel model){
		return this.dao.save(model);
	}
	@Override
	public Collection<PlayingRecordsModel> saveAll(Collection<PlayingRecordsModel> models){
		return this.dao.saveAll(models);
	}

	@Override
	public List<PlayingRecordsModel> query(QueryRequestEntity entity) {
		StringBuilder sql = new StringBuilder("1=1 ");
		List<Object> paramList = new ArrayList<>();
		if(StringUtils.isNotBlank(entity.getPlayingNo())){
			sql.append(" and playing_no=? ");
			paramList.add(entity.getPlayingNo());
		}
		sql.append(" order by create_time desc");
		Object[] params = paramList.toArray(new Object[paramList.size()] );
		return this.dao.findBySqlCondition(PlayingRecordsModel.class,
				sql.toString(), params,"",entity.getPagingInfo());
	}        
}