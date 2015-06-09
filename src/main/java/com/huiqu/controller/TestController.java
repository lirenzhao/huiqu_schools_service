package com.huiqu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huiqu.secrect.Sign;
import com.huiqu.synjones.Gdsc;

@Controller
public class TestController {
	@Autowired
	private Gdsc gdsc;
	
	@RequestMapping(value="/genkey",method = RequestMethod.GET)
	public ModelAndView home() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("abc", "127.0.0.1");//gdsc.getIp());
//		data.put("sys_code", gdsc.getSysCode().toString());
//		data.put("terminal_no", gdsc.getTerminalNo().toString());
		System.out.println("TestController home action：" + data.toString());
		return new ModelAndView("genkey",data);
	}
	@RequestMapping(value="/test/db",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> db(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("request", request.getParameterMap());
		result.put("message", "数据库连接测试成功！");
		return result;
	}
	
	@RequestMapping(value="/test/sios",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> sios(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("request", request.getParameterMap());
		result.put("message", "一卡通代理服务器SIOS测试成功！");
		return result;
	}
	@RequestMapping(value= "/sign")
	public @ResponseBody Map<String,Object> genKey(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String data = request.getParameter("data");
		String sign = Sign.sign(data);
		result.put("data", data);
		result.put("sign", sign);
		return result;
	}
}
