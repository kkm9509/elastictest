package com.example.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.search.SearchHit;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.repository.EsRepository;
import com.example.test.vo.EsGetVO;
import com.example.test.vo.TwitterVO;

@RestController
@RequestMapping("/es") 
public class EsTestController {

	@Autowired
	EsRepository esRepository;

	@RequestMapping("/search") 
	public ModelAndView search(@RequestParam Map<String, String> param) {
		
		ModelAndView mv = new ModelAndView("value");
		
		JSONObject jobj = new JSONObject();
		
		String indices = "meta";
		int from = 0;
		int size = 20;
		
		// 검색
		String type = "book";
		
		Map<String, Object> result;
		try {
			EsGetVO get = new EsGetVO();
			get.setIndices(indices);
			get.setType(type);
			get.setEsfrom(from);
			get.setEssize(size);
			
			result = esRepository.get(get);
	
			SearchHit [] hits = (SearchHit []) result.get("contentsList");
			
			jobj.put("code", "0000");
			JSONArray jarr = new JSONArray();
			for (int i = 0; i < hits.length; i++) {
				TwitterVO twitter = new TwitterVO();
				twitter.setId(hits[i].getId());
				twitter.setType(hits[i].getType());
				twitter.setSource(hits[i].getSource());
				jarr.put(twitter.getJSONObject());
				System.out.println("hits[i].getId()"+hits[i].getId());
				System.out.println("hits[i].getType()"+hits[i].getType());
				System.out.println("hits[i].getSource()"+hits[i].getSource());
			}
			jobj.put("list", jarr);
			
		} catch (Exception e) {
			jobj.put("code", "9998");
			jobj.put("message", e.toString());
			mv.addObject("value", jobj.toString());
			return mv;
		}
		
		mv.addObject("value", jobj.toString());
		return mv;
	}
	
	@RequestMapping("/put") 
	public ModelAndView put(@RequestParam Map<String, String> param) {
		
		ModelAndView mv = new ModelAndView("value");
		
		JSONObject jobj = new JSONObject();
		
		String indices = "meta";
		String type = "book";
		//String id = "1";
		
		String name = "hyunwoo";
		
		
		try {
			Map<String, Object> source = new HashMap<String, Object>();
			source.put("user", name);
			
			
			if (esRepository.put(indices, type, source)) {
				jobj.put("code", "0000");
			} else {
				jobj.put("code", "9990");
			}
		} catch (Exception e) {
			jobj.put("code", "9998");
			jobj.put("message", e.toString());
			mv.addObject("value", jobj.toString());
			return mv;
		}
		
		mv.addObject("value", jobj.toString());
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView update(@RequestParam Map<String, String> param) {
	
		ModelAndView mv = new ModelAndView("value");
		
		JSONObject jobj = new JSONObject();
		
		String indices = "meta";
		String type = "book";
		String id = "1";
		
		try {		
			if (esRepository.delete(indices, type, id)) {
				jobj.put("code", "0000");
			} else {
				jobj.put("code", "9990");
			}
		} catch (Exception e) {
			jobj.put("code", "9998");
			jobj.put("message", e.toString());
			mv.addObject("value", jobj.toString());
			return mv;
		}
		
		mv.addObject("value", jobj.toString());
		return mv;
	}
}