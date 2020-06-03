<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="index.title" /></title>
    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/index.js"></script>
</head>
<body>
<h1><spring:message code="index.title" /></h1>
<tag:feedbackmessage message="${deleteMessage}" styles="bg-yellow"/>
<br>
<c:url value="/game/new" var="newGameURL"/>
<a href="${newGameURL}"><spring:message code="index.newgame" /></a>
<c:url value="/game" var="queryURL"/>
<form action="${queryURL}" method="get">
<input type="text" name="q" value="${q}" placeholder="<spring:message code="index.placeholder" />">
<button type="submit"><spring:message code="index.button"/></button>
</form>
<table>
    <tr>
        <th>ID</th>
        <th><spring:message code="index.gametitle" /></th>
    </tr>
    <c:forEach var = "game"
               items = "${gameList}">
        <c:url value="/game/${game.id}" var="gameURL"/>
        <c:url value="/game/delete/${game.id}" var="deleteURL"/>
        <c:url value="/game/edit/${game.id}" var="editURL"/>
        <tr>
            <td><a href="${gameURL}">${game.id}</a></td>
            <td><a href="${gameURL}">${game.title}</a></td>
            <spring:message code="js.delete.confirm" arguments="${game.title}" var="deleteConfirm"/>
            <td><button onclick="deleteById('${deleteURL}', '${deleteConfirm}')"><spring:message code="index.delete"/></button></td>
            <td><a href="${editURL}"><spring:message code="index.edit" /></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>