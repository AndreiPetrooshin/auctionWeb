<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="locale.ui" var="lang" />

<html>
<head>
    <title>Hello</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index-style.css"/>
    <script src="${pageContext.request.contextPath}/assets/script/script.js"></script>
</head>
<body>
        <div class="hello">
            <c:if test="${sessionScope.user eq null}">
                <a class="start-button" type="button" href="${pageContext.request.contextPath}/login"><fmt:message key="start.login" bundle="${lang}"/></a>
                <a href="/registration"> <p><fmt:message key="header.registration" bundle="${lang}"/></p></a>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <a class="start-button" type="button"  href="${pageContext.request.contextPath}/home"><fmt:message key="start.enter" bundle="${lang}"/></a>
            </c:if>
        </div>
</body>
</html>
