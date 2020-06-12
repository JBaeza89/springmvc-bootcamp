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
<body>
<c:url value="/login" var="loginURL"/>
<div class="container">
    <form class="form-signin" method="post" action="${loginURL}">
        <h2 class="form-signin-heading"><spring:message code="login.header"/></h2>
        <p>
            <label for="username" class="sr-only"><spring:message code="login.username"/></label>
            <input type="text" id="username" name="username" class="form-control" placeholder="<spring:message code="login.username"/>" required autofocus>
        </p>
        <p>
            <label for="password" class="sr-only"><spring:message code="login.password"/></label>
            <input type="password" id="password" name="password" class="form-control" placeholder="<spring:message code="login.password"/>" required>
        </p>
        <input name="_csrf" type="hidden" value="36508f92-7aa3-4f3b-b228-b6c0fbd3beaf" />
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.submit"/></button>
    </form>
</div>
</body>
</html>