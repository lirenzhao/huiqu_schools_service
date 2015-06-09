package com.huiqu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("ver");
	}
	
	@RequestMapping(value="/grid",method = RequestMethod.GET)
	public ModelAndView flex() {
		return new ModelAndView("flexgrid");
	}

	@RequestMapping(value="/query")
	public @ResponseBody Map<String, Object> flexigrid() {
		logger.info("flexigrid controller executed.");
		Map<String, Object> result = new HashMap<String,Object>();
		
		ArrayList<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		for(int i = 0;i < 20; i++){
			Map<String, Object> row = new HashMap<String, Object>();
			Map<String, Object> cell = new HashMap<String, Object>();
			cell.put("datetime", i);
			cell.put("consume", 12.03);
			cell.put("income", 12.03);
			row.put("cell",cell);
			row.put("id",i);
			rows.add(row);
		}
		result.put("rows", rows);
		result.put("total", 20);
		result.put("page", "1");
		Map<String, Object> post = new HashMap<String, Object>();
		post.put("page", "1");
		post.put("qtype", "name");
		post.put("query", "");
		post.put("rp", "15");
		post.put("sortname", "datetime");
		post.put("sortorder", "asc");
		result.put("post", post);
		
		return result;
	}
}
