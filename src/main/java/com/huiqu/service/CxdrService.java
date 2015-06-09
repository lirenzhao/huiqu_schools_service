package com.huiqu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huiqu.framework.AbstractService;
import com.huiqu.jdbc.Trjn;

@Component("cxdr")
public class CxdrService extends AbstractService {
	@Autowired
	private Trjn trjn;

	// http://localhost:8080/service/service?m=cxdr&account=46894&s=123
	@Override
	protected Map<String, Object> run(Map<String, String> req) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		try {
			ret = trjn.search(req.get("no"), req.get("pa"));
			System.out.println(ret.toString());
			List<Map<String, Object>> data = cleanData(ret);
			result.put("data", data);
			result.put("ret", 0);
			result.put("message", "sucess");
		} catch (Exception ex) {
			result.put("ret", 1);
			result.put("message", "查询失败");
			ex.printStackTrace();
		}
		return result;
	}

	private List<Map<String, Object>> cleanData(List<Map<String, Object>> data) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for (int i = data.size() - 1; i >= 0; i--) {
			System.out.println(i);
			Map<String,Object> map = data.get(i);
			if (map.get("trancode").equals("22")) {
				if ((i > 0) && (data.get(i - 1).get("trancode").equals("16"))) {
					// 流水号差1 金额相同 交易类型是银行转账的保留
					String amt1 = map.get("AMT").toString();
					String amt2 = data.get(i - 1).get("AMT").toString();
					int jnum1 = Integer.parseInt(data.get(i).get("JNUM").toString());
					int jnum2 = Integer.parseInt(data.get(i - 1).get("JNUM").toString());
					System.out.println("amt1=" + amt1 + " amt2=" + amt2 + " jnum1=" + jnum1 + " jnum2=" + jnum2);
					if (amt1.equals(amt2)){
						//data.remove(i);
						System.out.println("break " + map);
						continue;
					}
				}
			}	
			System.out.println("put in result " + map);
			Map<String,Object> value = new HashMap<String, Object>();
			value.put("mercname", map.get("MERC"));
			value.put("datetime", map.get("EFFECTDATE").toString());
			value.put("amount", map.get("AMT").toString());
			result.add(value);
		}
		return result;
	}
}
