<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tags/customTag.tld" %>

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
    <ct:showLotList lotList="${requestScope.lotList}"/>
</main>

<%@include file="fragments/banner.jspf" %>
<%@include file="fragments/footer.jspf" %>

</body>
</html>
