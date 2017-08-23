package com.es;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.es.utils.HttpUtils;

@Controller
@RequestMapping("/http")
public class HttpSearch {

	@RequestMapping(value={"/create"}, method={RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> create(HttpServletRequest req){
		Map<String,Object> result = new HashMap<String, Object>();
		String resp = HttpUtils.doGet("http://127.0.0.1:9200");
		result.put("msg", resp);
		return result;
	}
	
}
