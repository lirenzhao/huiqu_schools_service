package com.huiqu.jdbc;

import java.util.List;
import java.util.Map;

public class HisTrjn extends AbstractDao {
	private String monthSql = "";
	public void setMonthSql(String value) {
		this.monthSql = value;
	}

	public List<Map<String, Object>> search(String account,String password,String month){
		System.out.println("HisTrjn sql " + monthSql + " account=" + account + " month = " + month);
		String month_table = String.format("webtrjn%02d",Integer.parseInt(month));
		String sql = String.format(monthSql, month_table);
		try{
			return jdbc_iddbuser.queryForList(sql,account);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
