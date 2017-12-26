<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../assets/css/normalize.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
    <script src="../../assets/script/script.js"></script>
    <title>LOT</title>
</head>

<body>
<%@include file="fragments/header.jspf" %>
<%@include file="fragments/navigation.jspf" %>

<main class="col-6 col-m-9 row">
    <c:if test="${sessionScope.user.id == requestScope.lot.user.id}" >
        <div id="fade" class="black-overlay"></div>
        <input type="button" value="Редактировать" onclick="openForm('redact-lot') ">
        <section id="redact-lot"  class="dialog">
            <form method="post" action="">
                <label>
                    <span>Тип:</span>
                    <input type="text" name="type">
                    <br>
                </label>
                <label>
                    <span>Имя:</span>
                    <input type="text" name="name">
                    <br>
                </label>
                <label>
                    <span>Описание:</span>
                    <textarea rows="15" cols="40"  name="description"></textarea>
                    <br>
                </label>
                <label>
                    <span>Начальная цена:</span>
                    <input type="number" name="startPrice">
                    <br>
                </label>
                <label>
                    <span>Состояние:</span>
                    <select name="state">
                        <option>trading</option>
                        <option>stop</option>
                    </select>
                    <br>
                </label>
                <input type="button" value="Закрыть" class="close-btn" onclick=closeForm("redact-lot")>
                <input type="submit" value="Сохранить" class="send-message">
            </form>
        </section>
    </c:if>
    <section class="col-12 col-m-12">
        <h1> ${requestScope.lot.name} </h1>
    </section>
    <section class="col-12 col-m-12">
        <h1> ФОТОГРАФИЯ </h1>
    </section>
    <section class="col-12 col-m-12">
        <p>${requestScope.lot.description}</p>
    </section>
    <section class="lot-price col-12 col-m-12">
        <p> START PRICE: ${requestScope .lot.startPrice}</p>
    </section>
    <section class="lot-price col-12 col-m-12">
        <form action="${pageContext.request.contextPath}/controller?command=makeBet&lotId=${requestScope.lot.id}"
              method="post">
            <section class="bet-from col-9 col-m-9">
                <label>
                    <input type="number" step="0.01" name="bet" title="Введите вашу ставку">
                </label>
            </section>
            <section class="col-3 col-m-3">
                <label>
                    <input class="button" type="submit">
                </label>
            </section>
        </form>
    </section>

    <section class="col-12 col-m-12">
        <h2> Ставки: </h2>
        <ul>
            <c:forEach items="${requestScope.bets}" var="bet">
                <li><c:out value="${bet.user.login}"/>
                    bet: <c:out value="${bet.bet}"/>
                </li>
            </c:forEach>
        </ul>
    </section>


</main>

<%@include file="fragments/banner.jspf" %>
<%@include file="fragments/footer.jspf" %>


</body>
</html>