<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/index.js"></script>
</head>
<body>
<h1>${title}</h1>
<c:url value="${not empty game.id ? '/game/edit/'.concat(game.id) : '/game/new'}" var="formURL"/>
<form:form action="${formURL}" method="post" modelAttribute="game">
    <s:message code="all.title"/>  <form:input path="title"/>
    <form:errors path="title" class="errorMessage"/><br>
    <s:message code="all.steamid"/>  <form:input path="steamId"/><br>
    <s:message code="all.description"/>  <form:textarea path="description"/><br>
    <button type="submit"><s:message code="savegame.send"/> </button>
</form:form>
<c:url value="/" var="indexURL"/>
<a href="${indexURL}"><s:message code="all.return"/> </a>
</body>
</html>