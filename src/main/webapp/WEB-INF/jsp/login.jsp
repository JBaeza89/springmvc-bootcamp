<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <tag:dependencies/>
    <script src="/js/index.js"></script>
</head>
<tag:classvars/>
<body class="${classBody}">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <c:url value="/" var="indexURL"/>
                <a class="nav-link" href="${indexURL}"><spring:message code="all.return"/></a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <form class="form-signin" method="post" action="${loginURL}">
        <h2 class="form-signin-heading"><spring:message code="login.header"/></h2>
        <tag:feedbackmessage message="${messageError}" styles="errorMessage"/>
        <p>
            <label for="username" class="sr-only"><spring:message code="login.username"/></label>
            <input type="text" id="username" name="username" class="form-control" placeholder="<spring:message code="login.username"/>" required autofocus>
        </p>
        <p>
            <label for="password" class="sr-only"><spring:message code="login.password"/></label>
            <input type="password" id="password" name="password" class="form-control" placeholder="<spring:message code="login.password"/>" required>
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.submit"/></button>
    </form>
</div>
</body>
</html>