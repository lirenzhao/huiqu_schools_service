<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello ${email} 
</h1>

<P>   <h2>The login user is ${name}</h2> </P>

<input type="button" value="logout" onclick="window.location.href='logout'"/>

</body>
</html>
