<%--
  Created by IntelliJ IDEA.
  User: draqo
  Date: 12.12.2017
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HOME</title>
    <link rel="stylesheet" href="../../assets/css/normalize.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
</head>
<body>
        <%@include file="fragments/header.jspf"%>
        <%@include file="fragments/navigation.jspf"%>


        <main class="col-6 col-m-9">
            <c:forEach items="${requestScope.lotList}" var="lot">
                <a href="${pageContext.request.contextPath}/controller?command=showLot&id=${lot.id}">
                    <section class="lot row">
                    <h2>${lot.name}</h2>
                    <div class="col-2">
                        ФОТОГРАФИЯ
                    </div>
                    <div class="col-8">
                        ${lot.description}
                    </div>
                    <div class="col-2">
                        PRICE: ${lot.startPrice}
                    </div>
                </section>
                </a>
                <hr>
            </c:forEach>
        </main>

        <%@include file="fragments/banner.jspf"%>
        <%@include file="fragments/footer.jspf"%>

</body>
</html>
