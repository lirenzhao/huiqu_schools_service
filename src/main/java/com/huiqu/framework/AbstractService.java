package com.huiqu.framework;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractService implements IService{

	@Override
	public Map<String, Object> exec(Map<String, String> req) {
		if(checkPass(req))
			return run(req);
		else
			return null;
	}

	//检查一卡通帐号密码
	private boolean checkPass(Map<String, String> req) {
		String no = req.get("no");
		String pa = req.get("pa");
		return true;
	}

	abstract protected Map<String,Object> run(Map<String,String> req) ;
}
