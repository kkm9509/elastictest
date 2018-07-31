package com.example.test.repository;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.utils.StrUtils;
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
		
		
		System.out.println("1.....>>>>"+search.getIndices());
		System.out.println("2.....>>>>"+search.getType());
		System.out.println("3.....>>>>"+srb.toString());
		// 검색
		SearchResponse response = srb.get();
		
		System.out.println("response"+response);
		
		// 데이터 가지고 오기 
    SearchHits hits = response.getHits();
    Map<String ,Object> result = new HashMap<>();
    result.put("contentsList", hits.getHits());

    return result;
}

	public boolean put(String indices, String type, String id, Map<String, Object> source) {
		// TODO Auto-generated method stub
		return false;
	}
}