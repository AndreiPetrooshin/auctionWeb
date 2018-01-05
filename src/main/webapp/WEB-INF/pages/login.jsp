<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="locale.ui" var="lang" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index-style.css"/>
    <script src="${pageContext.request.contextPath}/assets/script/script.js"></script>
</head>
<body>

<c:if test="${sessionScope.user eq null}">
    <c:if test="${requestScope.error != null}">
        <span class="error">${requestScope.error}</span>
        <hr>
    </c:if>
    <div class="col-4"></div>
    <div id="login_form" class="login-form col-4 col-m-12">
        <div class="col-12 col-m-12">
            <h1 class="col-6"><fmt:message key="header.login" bundle="${lang}"/></h1>
        </div>
        <form action="${pageContext.request.contextPath}/controller?command=login" method="post">
            <label for="login_login"><fmt:message key="form.login" bundle="${lang}"/></label>
            <input id="login_login" type="text" name="login"
                   title='<fmt:message key="form.login.title" bundle="${lang}"/>'
                   required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}">

            <label for="login_password"><fmt:message key="form.password" bundle="${lang}"/></label>
            <input id="login_password" type="password" name="password"
                   title='<fmt:message key="form.password.title" bundle="${lang}"/>'
                   required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{4,}">
            <input id="submit_login" type="submit" value='<fmt:message key="form.button.enter" bundle="${lang}"/>'
                   class="send-message col-3">
        </form>
    </div>
    <div class="col-4"></div>
</c:if>
</body>
</html>
