package com.hao.bot.enumtype;

/**
 * @author Harvey.He
 *
 */

public enum MsgHandlerType {
	/**
	 * 充分
	 */
	RECHARGE("充分规则","充分=",3),
	/**
	 * 提取分
	 */
	EXTRACT("提取分规则","提取分=",4),
	/**
	 * 查分
	 */
	QUERY("查分规则","查分",0),
	/**
	 * 下注
	 */
	ORDER("下注规则","下单",2),
	/**
	 * 取消下注
	 */
	CANCEL("取消下注规则","撤销单",0),
	/**
	 * 规则
	 */
	MSGRULE("规则","规则",0);
	
	private String name;
	private String rule;
	private Integer beginIndex;

	public String getName() {
		return name;
	}


	public String getRule() {
		return rule;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}





	private MsgHandlerType(String name,String rule,Integer beginIndex) {
		this.name = name;
		this.rule = rule;
		this.beginIndex = beginIndex;
	}
	
	public static MsgHandlerType get(String message){
		
		for (MsgHandlerType msgHandlerType : MsgHandlerType.values()) {
			boolean condition = (msgHandlerType.getBeginIndex() == 0 && msgHandlerType.getRule().equals(message)) ||
					(message.length() >= msgHandlerType.getBeginIndex() && msgHandlerType.getRule().equals(message.substring(0,msgHandlerType.getBeginIndex())));
			if(condition){
				return msgHandlerType;
			}
        }
		return null;
	}
	
	
}
