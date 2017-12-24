<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${sessionScope.user != null}">
        <a href="${pageContext.request.contextPath}/controller?command=logout"> LOGOUT </a>
        <a href="${pageContext.request.contextPath}/home"> HOME </a>
    <h1 id="already_login"> Вы уже зарегистрированны </h1>
</c:if>
