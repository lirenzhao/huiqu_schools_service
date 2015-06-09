package com.huiqu.synjones;

import java.util.HashMap;
import java.util.Map;

public class Gdsc {
	private String ip = "";
	private int port = 8500;

	private int sysCode = 23;
	private int terminalNo = 1;
	
	//com.huiqu.synjones.SynjonesTool gdsc = new com.huiqu.synjones.SynjonesTool();
	gdsc_test gdsc = new gdsc_test();
	
	public Map<String, Object> cardlost(long account, String password){
		Map<String, Object> result = new HashMap<String, Object>();
		int ret = gdsc.cardlost(ip, port, sysCode, terminalNo, account, password);
		result.put("ret", ret);
		result.put("message", gdsc.getError(ret));
		return result;
	}
	
	public Map<String, Object> cardUnlost(long account, String password){
		Map<String, Object> result = new HashMap<String, Object>();
		int ret = gdsc.cardUnlost(ip, port, sysCode, terminalNo, account, password);
		result.put("ret", ret);
		result.put("message", gdsc.getError(ret));
		return result;
	}
	
	public Map<String, Object> changePassword(long account, String oldPassword,String newPassword){
		Map<String, Object> result = new HashMap<String, Object>();
		int ret = gdsc.changePassword(ip, port, sysCode, terminalNo, account,  oldPassword, newPassword);
		result.put("ret", ret);
		result.put("message", gdsc.getError(ret));
		return result;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setSysCode(int sysCode) {
		this.sysCode = sysCode;
	}

	public void setTerminalNo(int terminalNo) {
		this.terminalNo = terminalNo;
	}
	
	public String getIp() {
		return ip;
	}

	public int getSysCode() {
		return sysCode;
	}

	public int getTerminalNo() {
		return terminalNo;
	}

	class gdsc_test{

		public int cardlost(String ip, int port, int sysCode, int terminalNo, long account, String password) {
			// TODO Auto-generated method stub
			return 0;
		}

		public Object getError(int ret) {
			// TODO Auto-generated method stub
			return "测试结果";
		}

		public int changePassword(String ip, int port, int sysCode, int terminalNo, long account, String oldPassword, String newPassword) {
			// TODO Auto-generated method stub
			return 0;
		}

		public int cardUnlost(String ip, int port, int sysCode, int terminalNo, long account, String password) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}
