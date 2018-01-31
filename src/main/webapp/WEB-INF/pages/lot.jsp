<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <title>LOT</title>
</head>

<body>
<%@include file="fragments/header.jspf" %>
<%@include file="fragments/navigation.jspf" %>

<main class="col-6 col-m-9 row">
    <c:if test="${sessionScope.user.id == requestScope.lot.user.id}">
        <div id="fade" class="black-overlay"></div>
        <input type="button" value='<fmt:message key="form.button.redact" bundle="${lang}"/>'
               onclick="openForm('redact-lot') ">
        <section id="redact-lot" class="dialog container clearfix">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=updateLot">
                <div class="row">
                    <input type="hidden" name="lotId" value="${requestScope.lot.id}">
                    <div class="col-25">
                        <label for="ltype"><fmt:message key="form.lot.type" bundle="${lang}"/>:</label>
                    </div>
                    <div class="col-75">
                        <select id="ltype" name="type">
                            <option value="meadow"><fmt:message key="form.lot.meadow" bundle="${lang}"/></option>
                            <option value="indoor"><fmt:message key="form.lot.indoor" bundle="${lang}"/></option>
                            <option value="forest"><fmt:message key="form.lot.forest" bundle="${lang}"/></option>
                            <option value="garden"><fmt:message key="form.lot.garden" bundle="${lang}"/></option>
                            <option value="cactus"><fmt:message key="form.lot.cactus" bundle="${lang}"/></option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lname"><fmt:message key="form.lot.name" bundle="${lang}"/>:</label>
                    </div>
                    <div class="col-75">
                        <input id="lname" type="text" name="name" value="${requestScope.lot.name}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label><fmt:message key="form.lot.description" bundle="${lang}"/>:</label>
                    </div>
                    <div class="col-75">
                        <textarea rows="7" cols="80" name="description">${requestScope.lot.description}</textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lstart-price"><fmt:message key="form.lot.start.price" bundle="${lang}"/>:</label>
                    </div>
                    <div class="col-75">
                        <input id="lstart-price" type="number" name="startPrice" value="${requestScope.lot.startPrice}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lstate"><fmt:message key="form.lot.state" bundle="${lang}"/>:</label>
                    </div>
                    <div class="col-75">
                        <select id="lstate" name="state">
                            <option selected value="${requestScope.lot.state}">${requestScope.lot.state}</option>
                            <option value="sold"><fmt:message key="form.lot.state.sold" bundle="${lang}"/></option>
                        </select>
                    </div>
                </div>
                <div class="row col-6">
                    <input type="button" value='<fmt:message key="form.button.close" bundle="${lang}"/>'
                           onclick=closeForm("redact-lot")>
                </div>
                <div class="row col-6">
                    <input type="submit" value='<fmt:message key="form.button.save" bundle="${lang}"/>'>
                </div>
            </form>
        </section>
    </c:if>
    <section class="col-12 col-m-12">
        <h1> ${requestScope.lot.name} </h1>
    </section>
    <section class="col-12 col-m-12">
        <p>${requestScope.lot.description}</p>
    </section>
    <section class="lot-price col-12 col-m-12">
        <p><fmt:message key="form.lot.start.price" bundle="${lang}"/>: ${requestScope .lot.startPrice}</p>
    </section>
    <section class="lot-price col-12 col-m-12">
        <form action="${pageContext.request.contextPath}/controller?command=makeBet&lotId=${requestScope.lot.id}"
              method="post">
            <c:if test="${requestScope.lot.user.id != sessionScope.user.id}">
                <c:if test="${sessionScope.errorBet}">
                    <span class="error"><fmt:message key="error.bet" bundle="${lang}"/></span>
                </c:if>
                <section class="bet-from col-12 col-m-9">
                    <label style="color:black" for="make-bet"><fmt:message key="form.bet.title" bundle="${lang}"/></label>
                    <input id="make-bet" type="number" step="0.01" name="bet" title="Введите вашу ставку">
                </section>
                <section class="col-3 col-m-3">
                    <input class="button" type="submit" value="<fmt:message key="make.bet" bundle="${lang}"/>">
                </section>
            </c:if>
        </form>
    </section>

    <section class="col-12 col-m-12">
        <h2> Ставки: </h2>
        <ul>
            <c:forEach items="${requestScope.bets}" var="bet">
                <li><c:out value="${bet.login}"/>
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