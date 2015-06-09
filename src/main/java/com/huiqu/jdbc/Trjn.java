package com.huiqu.jdbc;

import java.util.List;
import java.util.Map;

public class Trjn extends AbstractDao {
	private String sql = "";
	public void setSql(String value) {
		this.sql = value;
	}

	public List<Map<String, Object>> search(String account,String password){
		System.out.println("Trjn sql " + sql + " account=" + account );		
		try{
			return jdbc_iddbuser.queryForList(sql,account);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
