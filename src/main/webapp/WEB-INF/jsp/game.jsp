<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${game.title}</title>
    <tag:dependencies/>
    <script src="/js/simplegame.js"></script>
</head>
<body>

<tag:feedbackmessage message="${saveMessage}" styles="bg-yellow"/>
    <p>Id: ${game.id}</p>
    <p><spring:message code="all.title"/> ${game.title}</p>
    <div id="screenshot"></div>
    <p><spring:message code="all.description"/>${game.description}</p>
    <p><spring:message code="all.steamid"/><span id="steamId">${game.steamId}</span></p>
    <div id="news"></div>
    <c:url value="/" var="indexURL"/>
    <a href="${indexURL}"><spring:message code="all.return"/></a>
</body>
</html>