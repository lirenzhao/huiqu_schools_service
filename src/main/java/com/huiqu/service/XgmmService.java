package com.huiqu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.huiqu.framework.AbstractService;

@Component("xgmm")
public class XgmmService extends AbstractService {
private String no,pa,date;
	@Override
	protected Map<String, Object> run(Map<String, String> req) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("ret", 0);
		result.put("message", "sucess");
		return result;
	}
}
