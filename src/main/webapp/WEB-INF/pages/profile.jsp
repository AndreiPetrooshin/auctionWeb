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

<%@ include file="fragments/header.jspf" %>

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
    <c:if test="${requestScope.error != null}">
        <div class="col-12">${requestScope.error}</div>
    </c:if>
    <c:if test="${requestScope.about_myself eq true}">
        <%@include file="fragments/about-myself.jspf" %>
    </c:if>
    <c:if test="${requestScope.about_lots eq true}">
        <div class="row">
            <input type="button" value="Создать Лот" onclick="openForm('create-lot') ">
        </div>
        <section id="create-lot" class="dialog container clearfix">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=save&element=lot">
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
                        <label for="ldescription">Описание:</label>
                    </div>
                    <div class="col-75">
                        <textarea id="ldescription" rows="7" cols="80"
                                  name="description">${requestScope.lot.description}</textarea>
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
                <div class="row col-6">
                    <input type="button" value="Закрыть" onclick=closeForm("create-lot")>
                </div>
                <div class="row col-6">
                    <input type="submit" value="Сохранить">
                </div>
            </form>
        </section>
        <%@include file="fragments/about-lots.jspf" %>
    </c:if>
    <c:if test="${requestScope.about_addresses eq true}">
        <div class="row">
            <input type="button" value="Создать Лот" onclick="openForm('create-lot') ">
        </div>
        <section id="create-address" class="dialog container clearfix">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=save&element=lot">
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
                        <label for="ldescription">Описание:</label>
                    </div>
                    <div class="col-75">
                        <textarea id="ldescription" rows="7" cols="80"
                                  name="description">${requestScope.lot.description}</textarea>
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
                <div class="row col-6">
                    <input type="button" value="Закрыть" onclick=closeForm("create-lot")>
                </div>
                <div class="row col-6">
                    <input type="submit" value="Сохранить">
                </div>
            </form>
        </section>
        <%@include file="fragments/about-addresses.jspf" %>
    </c:if>
    <c:if test="${requestScope.about_cards eq true}">
        <div class="row">
            <input type="button" value="Создать Карту" onclick="openForm('create-card') ">
        </div>
        <section id="create-card" class="dialog container clearfix">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=save&element=card">
                <div class="row">
                    <div class="col-25">
                        <label for="card_name">Имя:</label>
                    </div>
                    <div class="col-75">
                        <input id="card_name" type="text" name="name" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="card_number">Номер карты:</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="card_number" name="card_number" required pattern="[0-9]{16}"/>
                    </div>
                </div>
                <div class="row col-6">
                    <input type="button" value="Закрыть" onclick=closeForm("create-card")>
                </div>
                <div class="row col-6">
                    <input type="submit" value="Сохранить">
                </div>
            </form>
        </section>
        <%@include file="fragments/about-cards.jspf" %>
    </c:if>
</main>

<aside class="col-3 hide"></aside>


</body>
</html>