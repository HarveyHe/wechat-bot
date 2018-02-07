package com.hao.bot.service.impl;    

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotExtractModel;
import com.hao.bot.service.BotExtractService;

/**
 * 
 * @author Harvey.He
 *
 */
@Service
public class BotExtractServiceImpl extends BaseServiceImpl
              implements BotExtractService {
     
	@Override
	public BotExtractModel get(java.lang.Integer id){
		return this.dao.get(BotExtractModel.class,id);
	}
	@Override
	public BotExtractModel save(BotExtractModel model){
		return this.dao.save(model);
	}
	@Override
	public Collection<BotExtractModel> saveAll(Collection<BotExtractModel> models){
		return this.dao.saveAll(models);
	}
	
	@Override
	public List<BotExtractModel> query(QueryRequestEntity entity) {
		StringBuilder sql = new StringBuilder("1=1 ");
		List<Object> paramList = new ArrayList<>();
		if(StringUtils.isNotBlank(entity.getToUserName())){
			sql.append(" and to_user_name=? ");
			paramList.add(entity.getToUserName());
		}
		if(StringUtils.isNotBlank(entity.getUserName())){
			sql.append(" and user_name like ? ");
			paramList.add("%" + entity.getUserName() + "%");
		}
		if(null != entity.getStatus()){
			sql.append(" and status = ? ");
			paramList.add(entity.getStatus());
		}
		sql.append(" order by create_time desc");
		Object[] params = paramList.toArray(new Object[paramList.size()] );
		return this.dao.findBySqlCondition(BotExtractModel.class,
				sql.toString(), params,"",entity.getPagingInfo());
	}
}