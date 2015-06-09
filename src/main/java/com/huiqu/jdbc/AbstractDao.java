package com.huiqu.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.huiqu.controller.ServiceController;

public abstract class AbstractDao {
	//@Autowired
	//protected JdbcTemplate jdbc_school;
	
	@Autowired
	protected JdbcTemplate jdbc_iddbuser;
	
	//@Autowired
	//protected JdbcTemplate jdbc_test;
	
	
	
}
