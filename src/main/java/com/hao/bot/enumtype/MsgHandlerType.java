package com.hao.bot.enumtype;

/**
 * @author Harvey.He
 *
 */

public enum MsgHandlerType {
	
	RECHARGE("充值规则","充分=",3),
	EXTRACT("提取分规则","提取分=",4),
	QUERY("查分规则","查分",0),
	ORDER("下注规则","下单",2),
	CANCEL("取消下注规则","撤销单",0),
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
	
//	public MsgHandlerType get(String type){
//		for (MsgHandlerType examType : MsgHandlerType.values()) {
//            if (examType.getType().equals(type)) {
//                return examType;
//            }
//        }
//		return null;
//	}
	
	
}
