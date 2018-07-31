package com.example.test.vo;

import java.util.Map;

import org.json.JSONObject;

public class TwitterVO {

	private String id = null;
	private String type = null;
	
	private String user = null;
	private String postDate = null;
	private String message = null;
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setSource(Map<String, Object> source) {
		if (source == null) return;
		
		try {
			setUser(source.get("user").toString());
			setPostDate(source.get("post_date").toString());
			setMessage(source.get("message").toString());
		} catch (Exception e) {
			
		}
	}
	
	public JSONObject getJSONObject() {
		JSONObject jobj = new JSONObject();
		jobj.put("id", getId());
		jobj.put("type", getType());
		jobj.put("user", getUser());
		jobj.put("post_date", getPostDate());
		jobj.put("message", getMessage());
		return jobj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
