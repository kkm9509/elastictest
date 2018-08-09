package com.example.test.repository;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.vo.EsGetVO;

@Repository
public class EsRepository {
	
    private Client client;

    @Autowired
    public EsRepository(Client client) {
        this.client = client;
    }
    
    public Map<String, Object> get(EsGetVO search) {

		SearchRequestBuilder srb = client.prepareSearch(search.getIndices())
				.setTypes(search.getType()).setFrom(search.getEsfrom()).setSize(search.getEssize());
		
		
		System.out.println("search1.....>>>>"+search.getIndices());
		System.out.println("search2.....>>>>"+search.getType());
		System.out.println("search3.....>>>>"+srb.toString());
		// 검색
		SearchResponse response = srb.get();
		
		System.out.println("response"+response);
		
		// 데이터 가지고 오기 
    SearchHits hits = response.getHits();
    Map<String ,Object> result = new HashMap<>();
    result.put("contentsList", hits.getHits());

    return result;
}

	public boolean put(String indices, String type , String id, Map<String, Object> source) {
		
		System.out.println("put.1.....>>>>"+indices);
		System.out.println("put.2.....>>>>"+type);
		System.out.println("put.3.....>>>>"+source.toString());
		
		IndexResponse response = client.prepareIndex(indices, type, id)
				.setSource(source)
				.get();
		return false;
	}

	public boolean delete(String indices, String type, String id) {
		
		System.out.println("del.1.....>>>>"+indices);
		System.out.println("del.2.....>>>>"+type);
		System.out.println("del.3.....>>>>"+id);
		DeleteResponse response = client.prepareDelete(indices, type, id)
    	        .get();
		
		return false;
	}

	
}