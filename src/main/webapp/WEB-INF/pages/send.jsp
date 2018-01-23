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
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index-style.css"/>
    <script src="${pageContext.request.contextPath}/assets/script/script.js"></script>
</head>
<body>

<c:if test="${sessionScope.user != null}">

    <div class="col-4"></div>
    <div id="registration" class="login-form col-4 col-m-12">
        <h1><fmt:message key="winner.address" bundle="${lang}"/></h1>
        <br>

        <c:if test="${requestScope.winner_address_error}">
            <label for="first-name" class="error"><fmt:message key="error" bundle="${lang}"/></label>
        </c:if>
        <form method="post"
              action="${pageContext.request.contextPath}/profile">
            <div class="row">
                <div class="col-25">
                    <label for="first-name"><fmt:message key="form.address.name" bundle="${lang}"/>:</label>
                </div>
                <div class="col-75">
                    <input id="first-name" disabled type="text" name="firstName"
                           value="${requestScope.address.firstName}">
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="second-name"><fmt:message key="form.address.surname" bundle="${lang}"/>:</label>
                </div>
                <div class="col-75">
                    <input id="second-name" disabled type="text" name="secondName"
                           value="${requestScope.address.secondName}">
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="last-name"><fmt:message key="form.address.lastname" bundle="${lang}"/>:</label>
                </div>
                <div class="col-75">
                    <input id="last-name" disabled type="text" name="lastName"
                           value="${requestScope.address.lastName}">
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="country"><fmt:message key="form.address.country" bundle="${lang}"/>:</label>
                </div>
                <div class="col-75">
                    <input id="country" disabled type="text" name="country" value="${requestScope.address.country}">
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="city"><fmt:message key="form.address.city" bundle="${lang}"/>:</label>
                </div>
                <div class="col-75">
                    <input id="city" disabled type="text" name="city" value="${requestScope.address.city}">
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="street"><fmt:message key="form.address.street" bundle="${lang}"/>:</label>
                </div>
                <div class="col-75">
                    <input id="street" disabled type="text" name="street" value="${requestScope.address.street}">
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="phone"><fmt:message key="form.address.phone" bundle="${lang}"/>:</label>
                </div>
                <div class="col-75">
                    <input id="phone" disabled type="number" name="phone" value="${requestScope.address.phone}">
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="postal_code"><fmt:message key="form.address.index" bundle="${lang}"/>:</label>
                </div>
                <div class="col-75">
                    <input id="postal_code" disabled type="text" name="postalCode"
                           value="${requestScope.address.postalCode}">
                </div>
            </div>
            <div class="row col-6">
                <a href="${pageContext.request.contextPath}/profile">
                    <input type="button" value="<fmt:message key="form.button.close" bundle="${lang}" />">
                </a>
            </div>
            <div class="row col-6">
                <input type="submit" value="<fmt:message key="form.button.send" bundle="${lang}" />">
            </div>
        </form>
    </div>
    <div class="col-4"></div>
</c:if>
</body>
</html>
