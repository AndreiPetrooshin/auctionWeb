<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="../assets/css/style-registration.css"/>
</head>
<body>

<c:if test="${sessionScope.user == null}">
    <div id="login">
        <form action="${pageContext.request.contextPath}/controller?command=registration" method="post">
            <fieldset class="clearfix">
                <header> REGISTRATION</header>
                <c:if test="${sessionScope.loginExist eq true}">
                    <p class="error"><c:out value="Данный логин уже существует"/></p>
                </c:if>
                <p><span class="fontawesome-user"></span>
                    <label>
                        <input type="text" value="Логин" name="login"
                               onBlur="if(this.value == '') this.value = 'Логин'"
                               onFocus="if(this.value == 'Логин') this.value = ''"
                               required>
                    </label></p>
                <c:if test="${sessionScope.emailExist eq true}">
                    <p class="error"><c:out value="Данный email уже существует"/></p>
                </c:if>
                <p><span class="fontawesome-lock"></span>
                    <label>
                        <input type="text" value="email" name="email"
                               onBlur="if(this.value == '') this.value = 'email'"
                               onFocus="if(this.value == 'email') this.value = ''"
                               required>
                    </label></p>

                <p><span class="fontawesome-lock"></span>
                    <label>
                        <input type="password" value="Пароль" name="password"
                               onBlur="if(this.value == '') this.value = 'Пароль'"
                               onFocus="if(this.value == 'Пароль') this.value = ''"
                               required pattern="[a-zA-Z0-9]{5,30}">
                    </label></p>

                <p><input type="submit" value="ВОЙТИ"></p>

            </fieldset>
        </form>
    </div>
</c:if>

    <jsp:include page="${pageContext.request.contextPath}/pages/fragments/alreadyLogin.jsp"/>
</body>
</html>
