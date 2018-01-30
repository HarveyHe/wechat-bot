package me.biezhi.wechat.model;

import java.util.Map;

import com.blade.kit.json.JSONArray;
import com.blade.kit.json.JSONObject;

public class WechatContact {

	// 微信联系人列表，可聊天的联系人列表
	private JSONArray memberList;
	private JSONArray contactList;
	private JSONArray groupList;
	
	private Map<String, JSONObject> contactMap;
	
	public WechatContact() {
	}

	public JSONArray getMemberList() {
		return memberList;
	}

	public void setMemberList(JSONArray memberList) {
		this.memberList = memberList;
	}

	public JSONArray getContactList() {
		return contactList;
	}

	public void setContactList(JSONArray contactList) {
		this.contactList = contactList;
	}

	public JSONArray getGroupList() {
		return groupList;
	}

	public void setGroupList(JSONArray groupList) {
		this.groupList = groupList;
	}

	public Map<String, JSONObject> getContactMap() {
		return contactMap;
	}

	public void setContactMap(Map<String, JSONObject> contactMap) {
		this.contactMap = contactMap;
	}
	
	
	
}
