<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
  <% 
        String name = (String) session.getAttribute("name");
        if (name == null) {
            name = "";
        }
    %>
<h1>Welcome <%= name %>!</h1>
<div class="links">
<p><a href="login.jsp"> Login </a></p>
<p><a href="registration.jsp"> Register</a> </p>
</div>

</body>
</html>