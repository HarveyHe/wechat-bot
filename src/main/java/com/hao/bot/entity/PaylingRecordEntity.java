package com.hao.bot.entity;


/**
 * 
 * @author Harvey.He
 *
 */
public class PaylingRecordEntity {
	/**
	 * 分数2
	 */
	private Integer score;
	
	/**
	 * 注1
	 */
	private Integer record;
	/**
	 * 最大数
	 */
	private Integer max;
	private Integer sec;
	private Integer min;
	
	/**
	 * 4，3，5
	 */
	private String recordValue;


	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getRecord() {
		return record;
	}

	public void setRecord(Integer record) {
		this.record = record;
	}

	public String getRecordValue() {
		return recordValue;
	}

	public void setRecordValue(String recordValue) {
		this.recordValue = recordValue;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getSec() {
		return sec;
	}

	public void setSec(Integer sec) {
		this.sec = sec;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}
	
	
	
	
}
		
