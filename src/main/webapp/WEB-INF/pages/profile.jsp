<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../assets/css/normalize.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
    <script src="../../assets/script/script.js"></script>
    <title>Profile</title>
</head>
<body>

        <%@ include file="fragments/header.jspf"%>

        <nav class="col-3 col-m-12 menu">
            <ul>
                <li><a href="${pageContext.request.contextPath}/controller?command=profile&about=myself">
                    О себе</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=profile&about=lots">
                    Мои лоты</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=profile&about=addresses">
                    Адресса</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=profile&about=cards">
                    Платежные системы</a></li>
            </ul>
        </nav>
        <div id="fade" class="black-overlay"></div>
        <main class="col-8 col-m-12">
            <c:if test="${requestScope.about_myself eq true}">
                <%@include file="fragments/about-myself.jspf"  %>
            </c:if>
            <c:if test="${requestScope.about_lots eq true}">
            <%@include file="fragments/about-lots.jspf"  %>
            </c:if>
            <c:if test="${requestScope.about_addresses eq true}">
            <%@include file="fragments/about-addresses.jspf"  %>
            </c:if>
            <c:if test="${requestScope.about_cards eq true}">
            <%@include file="fragments/about-cards.jspf"  %>
            </c:if>
        </main>

        <aside class="col-3 hide"></aside>




</body>
</html>