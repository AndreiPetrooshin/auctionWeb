<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="utf-8">
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
    <c:if test="${sessionScope.user.id == requestScope.lot.user.id}">
        <div id="fade" class="black-overlay"></div>
        <input type="button" value="Редактировать" onclick="openForm('redact-lot') ">
        <section id="redact-lot" class="dialog container clearfix">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=updateLot&id=${requestScope.lot.id}">
                <div class="row">
                    <div class="col-25">
                        <label for="ltype">Тип:</label>
                    </div>
                    <div class="col-75">
                        <select id="ltype" name="type">
                            <option>луговые</option>
                            <option>комнатные</option>
                            <option>лесные</option>
                            <option>садовые</option>
                            <option>кактусы</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lname">Имя:</label>
                    </div>
                    <div class="col-75">
                        <input id="lname" type="text" name="name" value="${requestScope.lot.name}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label>Описание:</label>
                    </div>
                    <div class="col-75">
                        <textarea rows="7" cols="80" name="description">${requestScope.lot.description}</textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lstart-price">Начальная цена:</label>
                    </div>
                    <div class="col-75">
                        <input id="lstart-price" type="number" name="startPrice" value="${requestScope.lot.startPrice}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lstate">Состояние:</label>
                    </div>
                    <div class="col-75">
                        <select id="lstate" name="state">
                            <option value="trading">таргуется</option>
                            <option value="sold">продано</option>
                        </select>
                    </div>
                </div>
                <div class="row col-6">
                    <input type="button" value="Закрыть" onclick=closeForm("redact-lot")>
                </div>
                <div class="row col-6">
                    <input type="submit" value="Сохранить">
                </div>
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
            <c:if test="${requestScope.lot.user.id != sessionScope.user.id}">
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
            </c:if>
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