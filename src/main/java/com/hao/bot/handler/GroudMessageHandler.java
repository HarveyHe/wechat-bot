package com.hao.bot.handler;

import org.apache.commons.lang3.StringUtils;

public class GroudMessageHandler {

	private String name;
	private String message;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@SuppressWarnings("unused")
	private GroudMessageHandler() {
		
	}
	public GroudMessageHandler(String name,String message ) {
		this.name = name;
		this.message = message;
		
		
	}
	
	//充分，提取分，查分，下单，撤销单
	public String handler(){
		if(StringUtils.isNotBlank(message)){
			if(message.startsWith("充分")){
				return this.recharge();
			}else if(message.startsWith("提取分")){
				return this.extract();
				
			}else if(message.startsWith("查分")){
				return this.query();
			}else if(message.startsWith("下单")){
				return this.order();
			}else if(message.startsWith("撤销单")){
				return this.cancel();
			}
			
		}
		return null;
	}
	

	/**
	 * 充值
	 */
	private String recharge(){
		String result =  "@" + name +"\n" +"正在充值";
		return result;
			
	}
	
	/**
	 * 查分
	 */
	private String query(){
		String result =  "@" + name +"\n" +"正在查分";
		return result;
	}
	
	/**
	 * 下单
	 */
	private String order(){
		String result =  "@" + name +"\n" +"正在下单";
		return result;
	}
	/**
	 * 撤销单
	 */
	private String cancel (){
		String result =  "@" + name +"\n" +"正在撤销单";
		return result;
	}
	/**
	 * 提取分
	 */
	private String extract  (){
		String result =  "@" + name +"\n" +"正在提取分";
		return result;
		
	}
}
