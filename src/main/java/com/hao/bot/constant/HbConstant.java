package com.hao.bot.constant;

import java.util.Date;

/**
 * 
 * @author Harvey.He
 *
 */
public class HbConstant {

	private HbConstant() {
		throw new IllegalStateException("Utility class");
	}
	/**
	 * 下注开始时间
	 */
	public static Date startTime = null;
	/**
	 * 下注结束时间
	 */
	public static Date endTime = null;
	
	
	/**
	 * 当前期数
	 */
	public static String currentPaylingNo;
	
	/**
	 * 是否可以下注
	 */
	public static boolean canBuy = false;
	
	public static Integer currentPaylingRecordId = null;
}
