<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><s:message code="error.title"/></title>
</head>
<body>
    <c:url value="/" var="indexURL"/>
    <a href="${indexURL}"><s:message code="all.return"/></a>
    <br><br>
    <h1><s:message code="error.standardmessage"/></h1>
    <h3>${messageError}</h3>
</body>
</html>