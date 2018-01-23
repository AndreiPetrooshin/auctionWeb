<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="language"
       value="${not empty sessionScope.lang ? sessionScope.lang : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale.ui" var="lang"/>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index-style.css"/>
    <script src="${pageContext.request.contextPath}/assets/script/script.js"></script>

</head>
<body>
<c:if test="${sessionScope.user == null}">
    <div class="col-4"></div>
    <div id="registration" class="login-form col-4 col-m-12">
        <h1><fmt:message key="header.registration" bundle="${lang}"/></h1>
        <br>

        <c:if test="${sessionScope.errorLogin}">
            <label for="login" class="error"><fmt:message key="error.login.exist" bundle="${lang}"/></label>
        </c:if>
        <c:if test="${sessionScope.incorrectLogin}">
            <label for="login" class="error"><fmt:message key="incorrect.login" bundle="${lang}"/></label>
        </c:if>
        <c:if test="${sessionScope.incorrectEmail}">
            <label for="login" class="error"><fmt:message key="incorrect.email" bundle="${lang}"/></label>
        </c:if>
        <c:if test="${sessionScope.incorrectPassword}">
            <label for="login" class="error"><fmt:message key="incorrect.password" bundle="${lang}"/></label>
        </c:if>
        <form id="registration-form" method="post"
              action="${pageContext.request.contextPath}/controller?command=registration">
            <label for="login"><fmt:message key="form.login" bundle="${lang}"/></label>
            <input id="login" type="text" name="login" title='<fmt:message key="form.login.title" bundle="${lang}"/>'
                   required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}">

            <label for="password"><fmt:message key="form.password" bundle="${lang}"/></label>
            <input id="password" type="password" name="password"
                   title='<fmt:message key="form.password.title" bundle="${lang}"/>'
                   required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{4,}">


            <label for="password_repeat"><fmt:message key="form.password.repeat" bundle="${lang}"/></label>
            <input id='password_repeat' type="password" name="password_repeat" required
                   title='<fmt:message key="form.password.repeat.title" bundle="${lang}"/>'
                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{6,}" onkeyup=checkPasswords()>

            <label for="email"><fmt:message key="form.email" bundle="${lang}"/> </label>
            <input id="email" type="text" name="email"
                   title='<fmt:message key="form.email.title" bundle="${lang}"/>' required
                   pattern="^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$">

            <input id="submit_registration" type="submit" disabled
                   value='<fmt:message key="form.button.enter" bundle="${lang}"/>' class="send-message col-3">
        </form>
    </div>
    <div class="col-4"></div>
</c:if>
</body>
</html>
