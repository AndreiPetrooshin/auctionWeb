<%--
  Created by IntelliJ IDEA.
  User: draqo
  Date: 12.12.2017
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MAIN</title>
</head>
<body>
    <h1> MAIN</h1>

    <a href="login">LOGIN</a>
    <a href="registration">REGISTRATION</a>
    <a href="home">HOME</a>
    <a href="${pageContext.request.contextPath}/controller?command=logout">HOME</a>
</body>
</html>
