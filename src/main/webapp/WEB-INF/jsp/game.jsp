<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${game.title}</title>
</head>
<body>
    <p>Id: ${game.id}</p>
    <p><s:message code="all.title"/> ${game.title}</p>
    <p><s:message code="all.description"/>${game.description}</p>
    <p><s:message code="all.steamid"/>${game.steamId}</p>
    <c:url value="/" var="indexURL"/>
    <a href="${indexURL}"><s:message code="all.return"/></a>
</body>
</html>