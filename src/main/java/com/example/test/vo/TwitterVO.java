package com.example.test.vo;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

@EntityScan
public class TwitterVO {

	@Id
	private String id = null;
	private String type = null;
	
	private String f_id = null;
	private String f_title = null;
	private String f_dt = null;
	
	private String name = null;
	private String age = null;
			
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	public String getF_title() {
		return f_title;
	}

	public void setF_title(String f_title) {
		this.f_title = f_title;
	}

	public String getF_dt() {
		return f_dt;
	}

	public void setF_dt(String f_dt) {
		this.f_dt = f_dt;
	}

	public void setSource(Map<String, Object> source) {
		if (source == null) return;
		
		try {
			setF_id(source.get("f_id").toString());
			setF_title(source.get("f_title").toString());
			setF_dt(source.get("f_dt").toString());
		} catch (Exception e) {
			
		}
	}
	
	public JSONObject getJSONObject() {
		JSONObject jobj = new JSONObject();
		jobj.put("id", getId());
		jobj.put("type", getType());
		jobj.put("f_id", getF_id());
		jobj.put("f_title", getF_title());
		jobj.put("f_dt", getF_dt());
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
