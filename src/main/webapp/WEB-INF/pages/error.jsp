<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Страница не найдена</title>
    <meta name="robots" content="noindex"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/error.css">
</head>
<body>
<div id="table">
    <div id="row">
        <div id="cell">
            <div class="tx">
                <h1>Ошибка 404</h1>
                <p>${requestScope}</p>
                <p>${sessionScope}</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>