package com.example.test.vo;

public class EsGetVO {

	private String indices = null;
	private int esfrom = 0;
	private int essize = 20;
	private String type = null;
	private String searchField = null; 
	private String searchPrefix = null;
	private String sortField = null;
	private String order = null;
	
	public String getIndices() {
		return indices;
	}
	
	public void setIndices(String indices) {
		this.indices = indices;
	}
	
	public int getEsfrom() {
		return esfrom;
	}
	
	public void setEsfrom(int esfrom) {
		this.esfrom = esfrom;
	}
	
	public int getEssize() {
		return essize;
	}
	
	public void setEssize(int essize) {
		this.essize = essize;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSearchField() {
		return searchField;
	}
	
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	
	public String getSearchPrefix() {
		return searchPrefix;
	}
	
	public void setSearchPrefix(String searchPrefix) {
		this.searchPrefix = searchPrefix;
	}
	
	public String getSortField() {
		return sortField;
	}
	
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
}
