package com.hao.bot.service.impl;    

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.service.impl.BaseServiceImpl;
import com.gsst.eaf.core.utils.DateUtils;
import com.hao.bot.entity.QueryRequestEntity;
import com.hao.bot.model.PlayingRecordsModel;
import com.hao.bot.service.PlayingRecordsService;
/**
 * 
 * @author Harvey.He
 *
 */
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
	@Override
	public Integer getCurrentDateTotalRecords() {
		BigInteger totalRecords = sqlDao.queryScalar("select count(*)" +
                " from playing_records " +
                " where DATE_FORMAT(create_time, '%Y%m%d') = DATE_FORMAT(CURDATE(), '%Y%m%d') ") ;
		return totalRecords.intValue();
	}
	@Override
	public PlayingRecordsModel genPlayingRecords() {
		Integer index = this.getCurrentDateTotalRecords();
		PlayingRecordsModel model = new PlayingRecordsModel();
		String payingNo = DateUtils.format(new Date(), "yyyyMMdd") + (index + 1);
		model.setPlayingNo( payingNo );
		Integer[] keyIndexs = this.getKeyIndex();
		model.setRecord1(keyIndexs[0]);
		model.setRecord2(keyIndexs[1]);
		model.setRecord3(keyIndexs[2]);
		model.setRecord4(keyIndexs[3]);
		model.setRecord5(keyIndexs[4]);
		model.setRecord6(keyIndexs[5]);
		model.setRecord7(keyIndexs[6]);
		model.setRecord8(keyIndexs[7]);
		model.setRecord9(keyIndexs[8]);
		model.setRecord10(keyIndexs[9]);
		return this.dao.save(model);
	} 
	private Integer[] getKeyIndex (){
		
		Integer[] keyIndexs =  new Integer[]{1,2,3,4,5,6,7,8,9,10};
		for (int i = keyIndexs.length-1; i >=0; i--) { 
			int randomIndex = (int) Math.floor(Math.random()*(i+1)); 
			int itemAtIndex = (int) keyIndexs[randomIndex]; 
			keyIndexs[randomIndex] = keyIndexs[i]; 
			keyIndexs[i] = itemAtIndex; 
		} 
		return keyIndexs;
	}
	
//	public static void main(String[] args) {
//		Object[] keyIndexs =  new Object[]{1,2,3,4,5,6,7,8,9,10};
//		for (int i = keyIndexs.length-1; i >=0; i--) { 
//			int randomIndex = (int) Math.floor(Math.random()*(i+1)); 
//			int itemAtIndex = (int) keyIndexs[randomIndex]; 
//			keyIndexs[randomIndex] = keyIndexs[i]; 
//			keyIndexs[i] = itemAtIndex; 
//		} 
//		for (int i = 0; i < keyIndexs.length; i++) {
//			System.out.println(keyIndexs[i]);
//		}
//	}
	
}