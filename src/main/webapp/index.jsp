<%--
  Created by IntelliJ IDEA.
  User: draqo
  Date: 10.12.2017
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>$Title$</title>
</head>
<body>
        <header>
            <a href="${pageContext.request.contextPath}/login" >LOGIN</a>
            <a href="${pageContext.request.contextPath}/registration">REGISTRATION</a>
            <%@ include file="WEB-INF/pages/fragments/alreadyLogin.jsp" %>
        </header>
</body>
</html>
