package com.hao.bot.entity;

import java.util.Date;

/**
 * 
 * @author Harvey.He
 *
 */
public class AwardEntity {
	/**
	 * 开奖时间
	 */
	private Date awardTime; 
	
	/**
	 * 开奖期数
	 */
	private String periodNumber;
	
	/**
	 * 开奖号码
	 * "2,8,4,9,10,3,1,5,7,6"
	 */
	private String awardNumbers;

	public Date getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(Date awardTime) {
		this.awardTime = awardTime;
	}

	public String getPeriodNumber() {
		return periodNumber;
	}

	public void setPeriodNumber(String periodNumber) {
		this.periodNumber = periodNumber;
	}

	public String getAwardNumbers() {
		return awardNumbers;
	}

	public void setAwardNumbers(String awardNumbers) {
		this.awardNumbers = awardNumbers;
	}
	
	
}
