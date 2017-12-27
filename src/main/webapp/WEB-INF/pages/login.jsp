<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../assets/css/style.css"/>
</head>
<body>
<c:if test="${sessionScope.user eq null}">
    <div class="col-4 col-m-1"></div>
    <div id="login" class="col-4 col-m-10">
        <span> LOGIN</span>
        <c:if test="${requestScope.error != null}">
            <span class="error">${requestScope.error}</span>
            <hr>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller?command=login" method="post">
            <label>
                <input type="text" value="Логин" name="login"
                       onBlur="if(this.value == '') this.value = 'Логин'"
                       onFocus="if(this.value == 'Логин') this.value = ''"
                       required>
            </label>
            <label>
                <input type="password" value="Пароль" name="password"
                       onBlur="if(this.value == '') this.value = 'Пароль'"
                       onFocus="if(this.value == 'Пароль') this.value = ''"
                       required>
            </label>
            <input type="submit" name="submit" value="ВОЙТИ">
        </form>
        <p>Нет аккаунта? &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/registration">Регистрация</a><span
                class="fontawesome-arrow-right"></span></p>

    </div>
    <div class="col-4 col-m-1 "></div>
</c:if>
</body>
</html>
