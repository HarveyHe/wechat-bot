package com.hao.bot.entity;

import java.io.Serializable;

import com.gsst.eaf.core.model.PagingInfo;

public class QueryRequestEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** uintegral_id
	 *标识
	 */ 
	private java.lang.Integer id;
	
	/** to_user_name
	 *微信会员id
	 */ 
	private java.lang.String toUserName;
	
	/** user_name
	 *会员名称
	 */ 
	private java.lang.String userName;
	
	private String playingNo;
	
	private java.lang.Integer status;
	
	/**
	 * 分页信息
	 */
	private PagingInfo pagingInfo;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getToUserName() {
		return toUserName;
	}

	public void setToUserName(java.lang.String toUserName) {
		this.toUserName = toUserName;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public PagingInfo getPagingInfo() {
		return pagingInfo;
	}

	public void setPagingInfo(PagingInfo pagingInfo) {
		this.pagingInfo = pagingInfo;
	}

	public String getPlayingNo() {
		return playingNo;
	}

	public void setPlayingNo(String playingNo) {
		this.playingNo = playingNo;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	
	
	    

}
