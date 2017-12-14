<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../assets/css/style-registration.css"/>
</head>
<body>
<c:if test="${sessionScope.user eq null}">
    <div id="login">
        <header> LOGIN</header>
        <c:if test="${requestScope.error eq true}">
            <span class="error">Не правильный пароль или логин</span>
            <hr>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller?command=login" method="post">
            <p><span class="fontawesome-user"></span>
                <label>
                    <input type="text" value="Логин" name="login"
                           onBlur="if(this.value == '') this.value = 'Логин'"
                           onFocus="if(this.value == 'Логин') this.value = ''"
                           required>
                </label></p>
            <p><span class="fontawesome-lock"></span>
                <label>
                    <input type="password" value="Пароль" name="password"
                           onBlur="if(this.value == '') this.value = 'Пароль'"
                           onFocus="if(this.value == 'Пароль') this.value = ''"
                           required>
                </label>
            </p>
            <input type="submit" name="submit" value="LOG_IN">
        </form>
        <p>Нет аккаунта? &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/registration">Регистрация</a><span
                class="fontawesome-arrow-right"></span></p>

    </div>
</c:if>

<jsp:include page="${pageContext.request.contextPath}/alreadyLogin"/>
</body>
</html>
