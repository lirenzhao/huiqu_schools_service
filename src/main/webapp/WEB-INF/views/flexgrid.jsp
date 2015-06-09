<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<link rel="stylesheet" type="text/css"
	href="resources/css/ui-lightness/jquery.css">
<script type="text/javascript" src="resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<script type="text/javascript" src="resources/js/flexigrid.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/flexgrid/flexigrid.css" />
<title>Login</title>

</head>
<body>
	<h1>FlexGrid Demo</h1>


	<table id="flex1" style="display: none"></table>
	<div id="data" style="">loading...</div>
	<script>
		$(function() {

		});
		$("#flex1").flexigrid({
			url : 'query',
			dataType : 'json',
			colModel : [ {
				display : '日期',
				name : 'datetime',
				width : 180,
				sortable : true,
				align : 'left'
			}, {
				display : '收入',
				name : 'income',
				width : 120,
				sortable : true,
				align : 'left'
			}, {
				display : '支出',
				name : 'consume',
				width : 130,
				sortable : true,
				align : 'left',
				hide : false
			} ],
			sortname : "datetime",
			sortorder : "asc",
			usepager : true,
			title : '月账单',
			useRp : true,
			rp : 15,
			showTableToggleBtn : true,
			width : 700,
			height : 200,
			query : "id=12&name=123"
		});
	</script>
</body>
</html>