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
<title>系统测试配置页</title>
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
</script>
</head>
<body>
	<h1>服务配置测试页[${abc}]</h1>
    <input type="text" value="${abc}" maxlength="40" id="aaa" name="aaa" />
	<div id="dialog-confirm" title="Empty the recycle bin?">
		<p>
			<span class="ui-icon ui-icon-alert"
				style="float: left; margin: 0 7px 20px 0;"></span>此页面仅作为部署测试使用?
		</p>
	</div>

	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">数据库测试</a></li>
			<li><a href="#tabs-2">代理服务器测试</a></li>
			<li><a href="#tabs-3" style="display: none">修改密码测试</a></li>
			<li><a href="#tabs-4" style="display: none">挂失测试</a></li>
		</ul>
		<div id="tabs-1">
			<table>
				<tr>
					<td>服务器地址</td>
					<td><input type="text" value="${sios_ip}" maxlength="20"
						id="server_ip" name="server_ip" /></td>
				</tr>
				<tr>
					<td>服务器端口</td>
					<td><input type="text" value="1521" maxlength="5"
						id="server_port" name="server_port" /></td>
				</tr>
				<tr>
					<td>SID</td>
					<td><input type="text" value="ORCL" maxlength="20"
						id="server_sid" name="server_sid" /></td>
				</tr>
				<tr>
					<td>用户名</td>
					<td><input type="text" value="${sys_code}" maxlength="20"
						id="server_username" name="server_username" /></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="password" value="password" maxlength="20"
						id="server_password" name="server_password" /></td>
				</tr>
			</table>
			<input type="submit" value="测试" onclick="db_test();" />
		</div>
		<div id="tabs-2">
			<table>
				<tr>
					<td>代理服务器地址</td>
					<td><input type="text" value="172.167.0.172" maxlength="20"
						id="sios_ip" name="sios_ip" /></td>
				</tr>
				<tr>
					<td>服务器端口</td>
					<td><input type="text" value="8500" maxlength="5"
						id="sios_port" name="sios_port" /></td>
				</tr>
				<tr>
					<td>系统代码</td>
					<td><input type="text" value="${sys_code}" maxlength="20"
						id="sys_code" name="sys_code" /></td>
				</tr>
				<tr>
					<td>终端编号</td>
					<td><input type="text" value="1" maxlength="20"
						id="terminal_no" name="" terminal_no""/></td>
				</tr>
			</table>
			<input type="submit" value="测试" onclick="sios_test();" />
		</div>
		<div id="tabs-3">
			<table>
				<tr>
					<td>一卡通帐号</td>
					<td><input type="text" value="12345" maxlength="20"
						id="account_no" name="account_no" /></td>
				</tr>
				<tr>
					<td>查询密码</td>
					<td><input type="password" value="123123" maxlength="6"
						id="inq_passoword" name="inq_passoword" /></td>
				</tr>
				<tr>
					<td>新查询密码</td>
					<td><input type="password" value="123123" maxlength="6"
						id="inq_passoword2" name="inq_passoword" /></td>
				</tr>
			</table>
			<input type="submit" value="测试" />
		</div>
		<div id="tabs-4">
			<table>
				<tr>
					<td>一卡通帐号</td>
					<td><input type="text" value="12345" maxlength="20"
						id="account_no3" name="account_no" /></td>
				</tr>
				<tr>
					<td>查询密码</td>
					<td><input type="password" value="123123" maxlength="6"
						id="inq_passoword3" name="" inq_passoword""/></td>
				</tr>
			</table>
			<input type="submit" value="挂失" />
		</div>
	</div>
	<hr>
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
				style="width: 300px;" /> <input type="button" value="Generate"
				onclick="generate_secret_key();" /></td>

		</tr>
		<tr>
			<td>公网地址</td>
			<td><input type="text"
				value="http://202.101.22.11:8888/synjones/service" maxlength="6"
				id="service_url" name="service_url" style="width: 300px" /></td>
		</tr>
	</table>
	<input type="submit" value="生成配置文件" />
	<br>
	<textarea rows="8" cols="80" id="result"></textarea>
</body>
</html>