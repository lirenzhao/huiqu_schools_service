package com.huiqu.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huiqu.framework.AbstractService;
import com.huiqu.jdbc.Account;

@Component("cx")
public class CxService extends AbstractService {
	@Autowired
	private Account account;
	
	@Override
	protected Map<String, Object> run(Map<String, String> req) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			result = account.get(req.get("account"),req.get("pwd"));
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
