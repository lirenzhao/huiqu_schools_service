package com.huiqu.jdbc;

import java.util.Map;

public class Account extends AbstractDao{
	private String sql = "";
	public void setSql(String value) {
		this.sql = value;
	}

	public Map<String, Object> get(String account,String password){
		System.out.println("Account get sql " + sql + " account=" + account );		
		try{
			return jdbc_iddbuser.queryForMap(sql,account);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
