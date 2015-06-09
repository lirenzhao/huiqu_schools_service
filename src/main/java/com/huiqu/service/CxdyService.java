package com.huiqu.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huiqu.framework.AbstractService;
import com.huiqu.jdbc.HisTrjn;

@Component("cxdy")
public class CxdyService extends AbstractService {
	@Autowired
	private HisTrjn hisTrjn;

	// http://localhost:8080/service/service?m=cxdy&account=48159&month=7&s=123
	@Override
	protected Map<String, Object> run(Map<String, String> req) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		try {
			ret = hisTrjn.search(req.get("no"), req.get("pa"),req.get("month"));
			System.out.println("result " + ret);
			initData(ret, req.get("month"));
			cleanData(ret);
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

	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

	private void initData(List<Map<String, Object>> input, String month) {
		data.clear();
		int mon = Integer.parseInt(month);
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, today.getYear());
		c.set(Calendar.MONTH, mon + 1);
		int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 1; i <= maxday; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("date", String.format("%02d", i));
			map.put("consume", "0");
			map.put("income", "0");
			data.add(map);
		}
	}

	private void setData(String day, double value) {
		int d = Integer.parseInt(day);
		Map<String, Object> map = data.get(d - 1);
		if (map != null) {
			double amt = 0;
			if (value > 0){
				amt = Double.parseDouble(map.get("income").toString());
				amt += value;
				map.put("income", amt);
			}
			else{
				amt = Double.parseDouble(map.get("consume").toString());
				amt += value;
				map.put("consume", amt);
			}
		}
		System.out.println("amp " + map);
	}

	private void cleanData(List<Map<String, Object>> input) {
		for (int i = 0; i < input.size(); i++) {
			Map<String, Object> map = input.get(i);
			String date = map.get("EFFECTDATE").toString();
			double amt = Double.parseDouble(map.get("AMT").toString());
			setData(date,amt);
		}
	}
}
