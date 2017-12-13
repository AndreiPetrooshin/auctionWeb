<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

</head>
<body>
<c:if test="${sessionScope.user != null}">
    <header>
        <nav><a href="${pageContext.request.contextPath}/controller?command=logout"> LOGOUT </a></nav>
        <nav><a href="${pageContext.request.contextPath}/home"> HOME </a></nav>
    </header>
    <h1 id="already_login"> Вы уже зарегистрированны </h1>

</c:if>
</body>


</html>
