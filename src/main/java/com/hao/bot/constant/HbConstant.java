package com.hao.bot.constant;

import java.util.Date;

public class HbConstant {

	private HbConstant() {
		throw new IllegalStateException("Utility class");
	}
	/**
	 * 下注开始时间
	 */
	public static Date startTime;
	/**
	 * 下注结束时间
	 */
	public static Date endTime;
	
	
	/**
	 * 当前期数
	 */
	public static String currentPaylingNo;
	public static Integer currentPaylingRecordId;
}
