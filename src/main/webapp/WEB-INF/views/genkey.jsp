<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<link rel="stylesheet" type="text/css"	href="resources/css/ui-lightness/jquery.css">
<script type="text/javascript"	src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript"	src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<script type="text/javascript" src="resources/js/flexigrid.pack.js"></script>
<link rel="stylesheet" type="text/css"	href="resources/css/flexgrid/flexigrid.pack.css" />
<title>系统配置页面</title>
<script>
	$(function() {
		$("#tabs").tabs();
		generate_secret_key();
	});

	function generate_secret_key() {
		$.post("/service/sign", {
			"data" : new Date().getTime()
		}, function(data) {
			$("#secret_key").val(data.sign);
		});
	}
	function db_test() {
		var ip = $("#server_ip").val();
		var port = $("#server_port").val();
		var sid = $("#server_sid").val();
		var username = $("#server_username").val();
		var password = $("#server_password").val();
		$.post("test/db", {
			"data" : {
				"ip" : ip,
				"port" : port,
				"sid" : sid,
				"user" : username,
				"pwd" : password
			}
		}, function(data) {
			alert(data.message);
		});
	}
	function sios_test() {
		var sios_ip = $("#sios_ip").val();
		var sios_port = $("#sios_port").val();
		var sys_code = $("#sys_code").val();
		var terminal_no = $("#terminal_no").val();
		$.post("test/sios", {
			"data" : {
				"sios_ip" : sios_ip,
				"sios_port" : sios_port,
				"sys_code" : sys_code,
				"terminal_no" : terminal_no
			}
		}, function(data) {
			alert(data.message);
		});
	}
	function genfile(){
		$("#result").val($("#school_name").val() + "|" + $("#secret_key").val() + "|" + $("#service_url").val());
	}
</script>
</head>
<body>
	<h1>服务配置测试页[${abc}]</h1>
    
	
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">生成系统安全密钥</a></li>
		</ul>
		
	</div>
	
	<table>
		<tr>
			<td>用户名称</td>
			<td><input type="text" value="中国大学" maxlength="20"
				id="school_name" name="school_name" style="width: 300px;" /></td>
		</tr>
		<tr>
			<td>安全密钥</td>
			<td><input type="text" readonly="true" value="123456"
				maxlength="20" id="secret_key" name="secret_key"
				style="width: 300px;" /><input type="button" value="生成系统安全密钥"
				onclick="generate_secret_key();" /></td>

		</tr>
		<tr>
			<td>公网地址</td>
			<td><input type="text"
				value="http://127.0.0.1:8080/service" maxlength="6"
				id="service_url" name="service_url" style="width: 300px" /></td>
		</tr>
	</table>
	<input type="submit" value="生成配置文件" onclick="genfile()"/>
	<br>
	<input type="text" id="result"></input>
	
</body>
</html>