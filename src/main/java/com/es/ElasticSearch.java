package com.es;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.indices.mapping.PutMapping;
import io.searchbox.strings.StringUtils;

@Controller
public class ElasticSearch {
	private static final Logger logger = LoggerFactory.getLogger(ElasticSearch.class);
	
	@Autowired
	private JestClientFactory jestFactory;
	
	@RequestMapping("/createIndex")
	@ResponseBody
	public Map<String, Object> createIndex(HttpServletRequest request){
		
		String index = request.getParameter("index");
		String type = request.getParameter("type");
		String content = request.getParameter("content");
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(index) || StringUtils.isBlank(type) ||
				StringUtils.isBlank(content)){
			result.put("code", 0);
			result.put("msg", "索引不完整");
			return result;
		}
		
		Map<String,Object> source = new HashMap<>();
		Map<String,Object> indexContent = new HashMap<>();
		Map<String,Object> message = new HashMap<>();
		message.put("message", content);
		indexContent.put("properties", message);
		source.put(type, indexContent);
		
		logger.info("创建索引");
		String con = JSON.toJSONString(source);
		logger.info(con);
		PutMapping putMapping = new PutMapping.Builder(index, type,con).build();
		try {
			JestClient client = jestFactory.getObject();
			
			JestResult res= client.execute(putMapping);
			logger.info(res.getErrorMessage());
//			logger.info(res.getJsonString());
		} catch (IOException e) {
			logger.error(e.getMessage() ,e);
			result.put("code", 0);
			result.put("msg", e.getMessage());
			return result;
		}
		result.put("code", 1);
		result.put("msg", "新建索引成功");
		return result;
		
	}
	
	
	@RequestMapping("/getIndex")
	@ResponseBody
	public Map<String, Object> getIndex(String indexName) throws IOException{
		logger.info("根据索引查询");
		JestClient client = jestFactory.getObject();
		
		PutMapping putMapping = new PutMapping.Builder(
		        "my_index",
		        "my_type",
		        "{ \"my_type\" : { \"properties\" : { \"message\" : {\"type\" : \"string\", \"store\" : \"yes\"} } } }"
		).build();
		JestResult res=client.execute(putMapping);
		
		logger.info(res.getErrorMessage());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("index", res);
		return result;
		
	}
}
