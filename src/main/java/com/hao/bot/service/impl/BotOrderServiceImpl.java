package com.hao.bot.service.impl;    

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.BotOrderModel;
import com.hao.bot.service.BotOrderService;

/**
 * @author Harvey.He
 *
 */
@Service
public class BotOrderServiceImpl extends BaseServiceImpl
              implements BotOrderService {
	@Override     
	public BotOrderModel get(java.lang.Integer id){
		return this.dao.get(BotOrderModel.class,id);
	}
	@Override
	public List<BotOrderModel> findByExample(BotOrderModel example,PagingInfo pagingInfo){
		if(example == null){
			example = new BotOrderModel();
		}
		return this.dao.findByExample(example,null,pagingInfo);
	}
	@Override
	public void delete(BotOrderModel model){
		this.dao.remove(model);
	}
	@Override
	public void deleteById(java.lang.Integer id){
		this.dao.removeByPk(BotOrderModel.class,id);
	}
	@Override
	public BotOrderModel save(BotOrderModel model){
		return this.dao.save(model);
	}
	@Override
	public Collection<BotOrderModel> saveAll(Collection<BotOrderModel> models){
		return this.dao.saveAll(models);
	}

	@Override
	public BotOrderModel getByToUserId(String id,Integer status) {
		BotOrderModel example = new BotOrderModel();
		example.setToUserName(id);
		example.setStatus(status);
		List<BotOrderModel> list = this.dao.findByExample(example);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<BotOrderModel> query(QueryRequestEntity entity) {
		StringBuilder sql = new StringBuilder("1=1 ");
		List<Object> paramList = new ArrayList<>();
		if(StringUtils.isNotBlank(entity.getToUserName())){
			sql.append(" and to_user_name=? ");
			paramList.add(entity.getToUserName());
		}
		if(StringUtils.isNotBlank(entity.getPlayingNo())){
			sql.append(" and playing_no=? ");
			paramList.add(entity.getPlayingNo());
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
		return this.dao.findBySqlCondition(BotOrderModel.class,
				sql.toString(), params,"",entity.getPagingInfo());
	}
	@Override
	public List<BotOrderModel> queryByStatusAndPayingNo(String payingNo, Integer status) {
		BotOrderModel example = new BotOrderModel();
		example.setPlayingNo(payingNo);
		example.setStatus(status);
		
		return this.dao.findByExample(example);
	}        
}