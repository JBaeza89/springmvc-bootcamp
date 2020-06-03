<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="error.title"/></title>
</head>
<body>
    <c:url value="/" var="indexURL"/>
    <a href="${indexURL}"><spring:message code="all.return"/></a>
    <br><br>
    <h1><spring:message code="error.standardmessage"/></h1>
    <h3><tag:feedbackmessage message="${messageError}"/></h3>
</body>
</html>