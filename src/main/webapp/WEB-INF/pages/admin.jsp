<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <script src="${pageContext.request.contextPath}/assets/script/script.js"></script>
</head>
<body>
<div id="fade" class="black-overlay"></div>
<%@include file="fragments/header.jspf" %>
<div class="col-1"></div>
<div class="col-8">
    <c:forEach items="${requestScope.lots}" var="lot">
        <section class="row">

            <section class="row admin-lots">
                <a href="${pageContext.request.contextPath}/controller?command=showLot&id=${lot.id}">
                    <div class="col-2">${lot.type}</div>
                    <div class="col-2">${lot.name}</div>
                    <div class="col-2"><fmt:message key="form.lot.start.price"
                                                    bundle="${lang}"/>: ${lot.startPrice}</div>
                    <div class="col-2">${lot.state}</div>
                    <div class="col-1">
                        <form action="${pageContext.request.contextPath}/controller?command=delete&element=lot"
                              method="post">
                            <input type="text" hidden name="id" value="${lot.id}" title="hidden">
                            <input type="submit" class="delete-button" value="X">
                        </form>
                    </div>
                </a>
                <div class="col-3">
                    <input type="button" id="edit-lot-${lot.id}"
                           value='<fmt:message key="form.button.redact" bundle="${lang}"/>'
                           onclick="openForm('redact-lot+${lot.id}') ">
                </div>
            </section>

        </section>

        <section id="redact-lot+${lot.id}" class="dialog container clearfix">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=updateLot&id=${lot.id}">
                <input type="hidden" name="userId" value="${lot.user.id}"/>
                <div class="row">
                    <div class="col-25">
                        <label for="ltype"><fmt:message key="form.lot.type" bundle="${lang}"/>:</label>
                    </div>
                    <div class="col-75">
                        <select id="ltype"  name="type">
                            <option selected name="${lot.type}">${lot.type}</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lname"><fmt:message key="form.lot.name" bundle="${lang}"/>:</label>
                    </div>
                    <div class="col-75">
                        <input id="lname" type="text" name="name"  value="${lot.name}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label><fmt:message key="form.lot.description" bundle="${lang}"/>:</label>
                    </div>
                    <div class="col-75">
                        <textarea rows="7" cols="80" name="description" >${lot.description}</textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lstart-price"><fmt:message key="form.lot.start.price" bundle="${lang}"/>:</label>
                    </div>
                    <div class="col-75">
                        <input id="lstart-price" type="number" name="startPrice"  value="${lot.startPrice}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="lstate"><fmt:message key="form.lot.state" bundle="${lang}"/>:</label>
                    </div>
                    <div class="col-75">
                        <select id="lstate" name="state">
                            <option value="new">new</option>
                            <option value="trading">trading</option>
                            <option value="rejected">rejected</option>
                            <option value="sold">sold</option>
                        </select>
                    </div>
                </div>
                <div class="row col-6">
                    <input type="button" value='<fmt:message key="form.button.close" bundle="${lang}"/>'
                           onclick=closeForm("redact-lot+${lot.id}")>
                </div>
                <div class="row col-6">
                    <input type="submit" value='<fmt:message key="form.button.save" bundle="${lang}"/>'>
                </div>
            </form>
        </section>
    </c:forEach>
</div>

<div>
    <%@include file="fragments/banner.jspf" %>
</div>


<%@include file="fragments/footer.jspf" %>


</body>
</html>
