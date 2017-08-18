package com.es;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ElasticSearch {
	private static final Logger logger = LoggerFactory.getLogger(ElasticSearch.class);
	
	@RequestMapping("/createIndex")
	@ResponseBody
	public Map<String, Object> createIndex(){
		logger.info("创建索引");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("index", "result");
		return result;
		
	}
	
	
	@RequestMapping("/getIndex")
	@ResponseBody
	public Map<String, Object> getIndex(String indexName){
		logger.info("根据索引查询");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("index", indexName);
		return result;
		
	}
}
