package com.huiqu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huiqu.framework.AbstractService;
import com.huiqu.jdbc.Trjn;
import com.huiqu.synjones.Gdsc;

@Component("gdsc")
public class GdscService extends AbstractService {
	@Autowired
	private Gdsc gdsc;

	// http://localhost:8080/service/service?m=gdsc&tag=0&acc=46894&pwd=123456&s=1234123121j1231
	@Override
	protected Map<String, Object> run(Map<String, String> req) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			int tag = Integer.parseInt(req.get("tag").toString());
			long account = Long.parseLong(req.get("acc").toString());
			String password = req.get("pwd").toString();
			switch (tag) {
			case 0: // do card lost
				data = gdsc.cardlost(account, password);
				break;
			case 1: // do card unlost
				data = gdsc.cardUnlost(account, password);
				break;
			case 2: // do change password
				String newpassword = req.get("newpwd").toString();
				data = gdsc.changePassword(account, password,newpassword);
				break;
			default:
				data.put("ret", 9999999);
				data.put("message", "不支持的操作");
				break;
			}

			System.out.println(data.toString());
			result.put("data", data);
			result.put("ret", 0);
			result.put("message", "sucess");
		} catch (Exception ex) {
			result.put("ret", 1);
			result.put("message", "操作失败");
			ex.printStackTrace();
		}
		return result;
	}
}
