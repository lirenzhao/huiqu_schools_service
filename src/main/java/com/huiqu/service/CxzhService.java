package com.huiqu.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huiqu.framework.AbstractService;
import com.huiqu.jdbc.User;

@Component("cxzh")
public class CxzhService extends AbstractService {
	@Autowired
	private User user;

	//http://localhost:8080/service/service?m=cxzh&sno=201121170010&pwd=81A7A3CB6AF74C34&s=123
	@Override
	protected Map<String, Object> run(Map<String, String> req) {
		Map<String, Object> result = new HashMap<String, Object>();
		//Map<String, Object> data = new HashMap<String, Object>();
		try{
			result = user.get(req.get("sno"),req.get("pwd"));
			//result.put("data", data);
			int ret = 0;
			if(result == null) ret = 1;
			result.put("ret", ret);
			result.put("message", "sucess");
		}catch(Exception ex){
			result.put("ret", 1);
			result.put("message", "密码验证失败");
		}
		return result;
	}
}
