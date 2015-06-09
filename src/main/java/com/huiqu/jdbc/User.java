package com.huiqu.jdbc;

import java.util.Map;

public class User extends AbstractDao{
	private String select = "";
	public void setSelect(String select) {
		this.select = select;
		
	}
	public Map<String,Object> get(String sno,String password){
		System.out.println(" sql " + select + " sno=" + sno + " password = " + password);
		try{
			return jdbc_iddbuser.queryForMap(select,sno,password);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
