package com.hao.bot.service.impl;    

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotIntegralModel;
import com.hao.bot.service.BotIntegralService;

/**
 * 
 * @author Harvey.He
 *
 */
@Service
public class BotIntegralServiceImpl extends BaseServiceImpl
              implements BotIntegralService {
        
	@Override
	public BotIntegralModel get(java.lang.Integer id){
		return this.dao.get(BotIntegralModel.class,id);
	}
	@Override
	public BotIntegralModel save(BotIntegralModel model){
		return this.dao.save(model);
	}
	@Override
	public Collection<BotIntegralModel> saveAll(Collection<BotIntegralModel> models){
		return this.dao.saveAll(models);
	}

	@Override
	public BotIntegralModel getByToUserId(String id) {
		BotIntegralModel example = new BotIntegralModel();
		example.setToUserName(id);
		List<BotIntegralModel> list = this.dao.findByExample(example);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<BotIntegralModel> query(QueryRequestEntity entity) {
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
		sql.append(" order by create_time desc");
		Object[] params = paramList.toArray(new Object[paramList.size()] );
		return this.dao.findBySqlCondition(BotIntegralModel.class,
				sql.toString(), params,"",entity.getPagingInfo());
	}        
}