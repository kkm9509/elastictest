package com.example.test.repository;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
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
    
    public boolean delete(String indices, String type, String id) {
    	try {
    		 
    		DeleteResponse response = client.prepareDelete(indices, type, id)
	    	        .get();
	    	
	    	if (response == null) return false;
	    return response.getShardInfo().getSuccessful() == 1;
	    	
    	} catch (Exception e) {
    		return false;
    	}
    }
    
    public boolean put(String indices, String type, String id, Map<String, Object> source) {
	    	try {
		    	IndexResponse response = client.prepareIndex(indices, type)
		    			.setSource(source)
		    			.setId(id)
		    	        .get();
		    	
		    	if (response == null) return false;
		    return response.getShardInfo().getSuccessful() == 1;
		    	
	    	} catch (Exception e) {
	    		return false;
	    	}
    }
    
    public Map<String, Object> get(EsGetVO search) {

    		SearchRequestBuilder srb = client.prepareSearch(search.getIndices())
              .setFrom(search.getEsfrom()).setSize(search.getEssize());
    		
    		// 타입 
    		if (StrUtils.isEmpty(search.getType()) == false)
    			srb.setTypes(search.getType());
    		
    		// 정렬 
    		if (StrUtils.isEmpty(search.getSortField()) == false ||
    				StrUtils.isEmpty(search.getOrder()) == false) {
    			if (search.getOrder().equalsIgnoreCase("DESC"))
        			srb.addSort(search.getSortField(), SortOrder.DESC);
    			else 
        			srb.addSort(search.getSortField(), SortOrder.ASC);
    		}
    		
    		// Prefix 검색 
    		if (StrUtils.isEmpty(search.getSearchField()) == false || StrUtils.isEmpty(search.getSearchPrefix()) == false) 
    			srb.setQuery(QueryBuilders.prefixQuery(search.getSearchField(), search.getSearchPrefix()));    		
    		
    		// 검색
    		SearchResponse response = srb.get();
    		
    		// 데이터 가지고 오기 
        SearchHits hits = response.getHits();
        Map<String ,Object> result = new HashMap<>();
        result.put("contentsList", hits.getHits());

        return result;
    }
}