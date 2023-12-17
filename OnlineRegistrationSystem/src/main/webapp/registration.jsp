<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%@include file="header.jsp" %>
<h2>Registration Form</h2>
<form action="register" method='post'>
    Name:<input type='text' name ='name'><br>
    Email: <input type ="text" name ="email"><br>
    Password: <input type="password" name="password"><br>
<input type="submit" value="Register">
</form>

</body>
</html>