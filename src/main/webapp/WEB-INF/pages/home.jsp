<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HOME</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <script src="${pageContext.request.contextPath}/assets/script/script.js"></script>
</head>
<body>
<%@include file="fragments/header.jspf" %>
<%@include file="fragments/navigation.jspf" %>

<div id="fade" class="black-overlay"></div>
<main class="col-6 col-m-9">
    <c:forEach items="${requestScope.lotList}" var="lot">
        <a href="${pageContext.request.contextPath}/controller?command=showLot&id=${lot.id}">
            <section class="lot row main-lots">
                <div class="col-2">
                        ${lot.name}
                </div>
                <div class="col-8">
                        ${lot.description}
                </div>
                <div class="col-2">
                    <fmt:message key="lots.price" bundle="${lang}"/>: ${lot.startPrice}
                </div>
            </section>
        </a>
    </c:forEach>
</main>

<%@include file="fragments/banner.jspf" %>
<%@include file="fragments/footer.jspf" %>

</body>
</html>
