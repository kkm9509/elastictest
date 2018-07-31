package com.example.test.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "kyungmin", type = "book")
public class EsGetVO {

	@Id
	private String id;
	private String title;
	private String author;
	private String releaseDate;
	
	public EsGetVO() {
		
	}
	
	public EsGetVO(String id, String title, String author, String releaseDate)
	
	
}
