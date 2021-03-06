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
    <script src="/js/game.js"></script>
</head>
<tag:classvars/>
<body class="${classBody}">
<tag:navigationbar/>
<tag:feedbackmessage message="${saveMessage}"/>
    <p>Id: ${game.id}</p>
    <p><spring:message code="all.title"/> ${game.title}</p>
    <div id="screenshot"></div>
    <p><spring:message code="all.description"/>${game.description}</p>
    <p><spring:message code="all.steamid"/>${game.steamId}</p>
    <div id="news"></div>

    <c:url value="/game/steam/details/${game.steamId}" var="urlDetails"/>
    <input type="hidden" id="urlDetails" data="${urlDetails}">
    <c:url value="/game/steam/news/${game.steamId}" var="urlNews"/>
    <input type="hidden" id="urlNews" data="${urlNews}">


</body>
</html>