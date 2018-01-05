<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <title>Profile</title>
</head>
<body>

<%@ include file="fragments/header.jspf" %>

<nav class="col-3 col-m-12 menu">
    <ul>
        <li><a href="${pageContext.request.contextPath}/controller?command=profile&about=myself">
            <fmt:message key="profile.about.myself" bundle="${lang}" />
        </a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=profile&about=lots">
            <fmt:message key="profile.about.lots" bundle="${lang}" />
        </a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=profile&about=addresses">
            <fmt:message key="profile.about.addresses" bundle="${lang}" />
        </a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=profile&about=cards">
            <fmt:message key="profile.about.payments" bundle="${lang}" />
        </a></li>
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
            <input type="button" value='<fmt:message key="profile.button.add.lot" bundle="${lang}" />' onclick="openForm('create-lot') ">
        </div>
        <section id="create-lot" class="dialog container clearfix">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=save&element=lot">
                <div class="row">
                    <div class="col-25">
                        <label for="ltype"><fmt:message key="form.lot.type" bundle="${lang}" />:</label>
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
                        <label for="lname"><fmt:message key="form.lot.name" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="lname" type="text" name="name" value="${requestScope.lot.name}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="ldescription"><fmt:message key="form.lot.description" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <textarea id="ldescription" rows="7" cols="80"
                                  name="description">${requestScope.lot.description}</textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lstart-price"><fmt:message key="form.lot.start.price" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="lstart-price" type="number" name="startPrice" value="${requestScope.lot.startPrice}">
                    </div>
                </div>
                <div class="row col-6">
                    <input type="button" value="<fmt:message key="form.button.close" bundle="${lang}" />" onclick=closeForm("create-lot")>
                </div>
                <div class="row col-6">
                    <input type="submit" value="<fmt:message key="form.button.save" bundle="${lang}" />">
                </div>
            </form>
        </section>
        <%@include file="fragments/about-lots.jspf" %>
    </c:if>
    <c:if test="${requestScope.about_addresses eq true}">
        <div class="row">
            <input type="button" value="<fmt:message key="profile.button.add.address" bundle="${lang}" />" onclick="openForm('create-address') ">
        </div>
        <section id="create-address" class="dialog container clearfix">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=save&element=address">
                <div class="row">
                    <div class="col-25">
                        <label for="first-name"><fmt:message key="form.address.name" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="first-name" required type="text" name="firstName" value="${requestScope.address.firstName}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="second-name"><fmt:message key="form.address.surname" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="second-name" required type="text" name="secondName" value="${requestScope.address.secondName}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="last-name"><fmt:message key="form.address.lastname" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="last-name" required type="text" name="lastName" value="${requestScope.address.lastName}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="country"><fmt:message key="form.address.country" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="country" required type="text" name="country" value="${requestScope.address.country}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="city"><fmt:message key="form.address.city" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="city" required type="text" name="city" value="${requestScope.address.city}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="street"><fmt:message key="form.address.street" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="street" required type="text" name="street" value="${requestScope.address.street}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="phone"><fmt:message key="form.address.phone" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="phone" required type="number" name="phone" value="${requestScope.address.phone}" pattern="/^([+]?[0-9\s-\(\)]{3,25})*$/i;">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="postal_code"><fmt:message key="form.address.index" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="postal_code" required type="text" name="postalCode" value="${requestScope.address.postalCode}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="active"><fmt:message key="form.address.active" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="active" required type="checkbox" name="active" value="${requestScope.address.postalCode}">
                    </div>
                </div>
                <div class="row col-6">
                    <input type="button" value="<fmt:message key="form.button.close" bundle="${lang}" />" onclick=closeForm("create-address")>
                </div>
                <div class="row col-6">
                    <input type="submit" value="<fmt:message key="form.button.save" bundle="${lang}" />">
                </div>
            </form>
        </section>
        <%@include file="fragments/about-addresses.jspf" %>
    </c:if>
    <c:if test="${requestScope.about_cards eq true}">
        <div class="row">
            <input type="button" value="<fmt:message key="profile.button.add.card" bundle="${lang}" />" onclick="openForm('create-card') ">
        </div>
        <section id="create-card" class="dialog container clearfix">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=save&element=card">
                <div class="row">
                    <div class="col-25">
                        <label for="card_name"><fmt:message key="form.card.name" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input id="card_name" type="text" name="name" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="card_number"><fmt:message key="form.card.number" bundle="${lang}" />:</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="card_number" name="card_number" required pattern="[0-9]{16}"/>
                    </div>
                </div>
                <div class="row col-6">
                    <input type="button" value="<fmt:message key="form.button.close" bundle="${lang}" />" onclick=closeForm("create-card")>
                </div>
                <div class="row col-6">
                    <input type="submit" value="<fmt:message key="form.button.save" bundle="${lang}" />">
                </div>
            </form>
        </section>
        <%@include file="fragments/about-cards.jspf" %>
    </c:if>
</main>

<aside class="col-3 hide"></aside>

        <%@include file="fragments/footer.jspf"%>


</body>
</html>