<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="../../assets/css/style-registration.css"/>
</head>
<body>
<c:if test="${sessionScope.user == null}">
    <div id="login">
        <form action="${pageContext.request.contextPath}/controller?command=registration" method="post">
            <fieldset class="clearfix">
                <header> REGISTRATION</header>
                <c:if test="${sessionScope.loginError != null}">
                    <p class="error"><c:out value="${sessionScope.loginError}"/></p>
                </c:if>
                <c:if test="${sessionScope.incorrect_login != null}">
                    <p class="error"><c:out value="${sessionScope.incorrect_login}"/></p>
                </c:if>
                <p><span class="fontawesome-user"></span>
                    <label>
                        <input type="text" value="Логин" name="login"
                               onBlur="if(this.value == '') this.value = 'Логин'"
                               onFocus="if(this.value == 'Логин') this.value = ''"
                               required pattern="[a-zA-Z][a-zA-Z0-9]{5,30}"
                               title="Login should starts with letter and consist 5 or more characters">
                    </label></p>
                <c:if test="${sessionScope.incorrect_email != null}">
                    <p class="error"><c:out value="${sessionScope.incorrect_email}"/></p>
                </c:if>
                <p><span class="fontawesome-lock"></span>
                    <label>
                        <input type="text" value="email" name="email"
                               onBlur="if(this.value == '') this.value = 'email'"
                               onFocus="if(this.value == 'email') this.value = ''"
                               required pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                               title="Email wrong syntax">
                    </label></p>
                <c:if test="${sessionScope.incorrect_password != null}">
                    <p class="error"><c:out value="${sessionScope.incorrect_password}"/></p>
                </c:if>
                <p><span class="fontawesome-lock"></span>
                    <label>
                        <input type="password" value="Пароль" name="password"
                               onBlur="if(this.value == '') this.value = 'Пароль'"
                               onFocus="if(this.value == 'Пароль') this.value = ''"
                               required pattern="[a-zA-Z0-9]{5,30}"
                               title="Password should consist 5 or more characters">
                    </label></p>

                <p><input type="submit" value="ВОЙТИ"></p>

            </fieldset>
        </form>
    </div>
</c:if>
</body>
</html>
