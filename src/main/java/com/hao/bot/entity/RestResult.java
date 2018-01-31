package com.hao.bot.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.gsst.eaf.core.model.PagingInfo;
import com.gsst.eaf.core.utils.JSON;

public class RestResult<T> {
	private Integer code;
	private String message;
	private T data;
	private PagingInfo pagingInfo;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public PagingInfo getPagingInfo() {
		return pagingInfo;
	}
	public void setPagingInfo(PagingInfo pagingInfo) {
		this.pagingInfo = pagingInfo;
	}
	
	private static RestResult<?> parse(JsonNode node) throws Exception {
		RestResult<?> result = new RestResult<>();
		JsonNode _node = node.get("code");
		if(_node != null && _node.getNodeType() == JsonNodeType.NUMBER) {
			result.setCode(_node.asInt());
		}
		_node = node.get("message");
		if(_node != null && _node.getNodeType() == JsonNodeType.STRING) {
			result.setMessage(_node.asText());
		}
		_node = node.get("pagingInfo");
		if(_node != null && _node.getNodeType() == JsonNodeType.OBJECT) {
			PagingInfo pagingInfo = JSON.getObjectMapper().readValue(_node.traverse(), PagingInfo.class);
			result.setPagingInfo(pagingInfo);
		}
		return result;
	}
	
	public static <T> RestResult<T> parse(String json,TypeReference<T> dataTypeRef) throws Exception {
		JsonNode node = JSON.getObjectMapper().readTree(json);
		if(node.getNodeType() != JsonNodeType.OBJECT) return null;
		@SuppressWarnings("unchecked")
		RestResult<T> result = (RestResult<T>)parse(node);
		JsonNode _node = node.get("data");
		if(_node != null) {
			T data = JSON.getObjectMapper().readValue(_node.traverse(), dataTypeRef);
			result.setData(data);
		}
		return result;
	}
	
	public static <T> RestResult<T> parse(String json,Class<T> clazz) throws Exception {
		JsonNode node = JSON.getObjectMapper().readTree(json);
		if(node.getNodeType() != JsonNodeType.OBJECT) return null;
		@SuppressWarnings("unchecked")
		RestResult<T> result = (RestResult<T>)parse(node);
		JsonNode _node = node.get("data");
		if(_node != null) {
			T data = JSON.getObjectMapper().readValue(_node.traverse(), clazz);
			result.setData(data);
		}
		return result;
	}
}

