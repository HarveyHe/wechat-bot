package com.hao.bot.service.impl;    

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotRechargeModel;
import com.hao.bot.service.BotRechargeService;

/**
 * 
 * @author Harvey.He
 *
 */
@Service
public class BotRechargeServiceImpl extends BaseServiceImpl
              implements BotRechargeService {
	@Override       
	public BotRechargeModel get(java.lang.Integer id){
		return this.dao.get(BotRechargeModel.class,id);
	}
	@Override
	public List<BotRechargeModel> findByExample(BotRechargeModel example,PagingInfo pagingInfo){
		if(example == null){
			example = new BotRechargeModel();
		}
		return this.dao.findByExample(example,null,pagingInfo);
	}
	@Override
	public void delete(BotRechargeModel model){
		this.dao.remove(model);
	}
	@Override
	public void deleteById(java.lang.Integer id){
		this.dao.removeByPk(BotRechargeModel.class,id);
	}
	@Override
	public BotRechargeModel save(BotRechargeModel model){
		return this.dao.save(model);
	}
	@Override
	public Collection<BotRechargeModel> saveAll(Collection<BotRechargeModel> models){
		return this.dao.saveAll(models);
	}

	@Override
	public List<BotRechargeModel> query(QueryRequestEntity entity) {
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
		return this.dao.findBySqlCondition(BotRechargeModel.class,
				sql.toString(), params,"",entity.getPagingInfo());
	}
}