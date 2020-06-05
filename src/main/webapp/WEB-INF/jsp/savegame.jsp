<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code='${title}'/></title>
    <tag:dependencies/>
    <script src="/js/index.js"></script>
</head>
<body>
<h1><spring:message code='${title}'/></h1>
<c:url value="${not empty game.id ? '/game/edit/'.concat(game.id) : '/game/new'}" var="formURL"/>
<form:form action="${formURL}" method="post" modelAttribute="game">
    <spring:message code="all.title"/>  <form:input path="title"/>
    <form:errors path="title" class="errorMessage"/><br>
    <spring:message code="all.steamid"/>  <form:input path="steamId"/><br>
    <spring:message code="all.description"/>  <form:textarea path="description"/><br>
    <button type="submit"><spring:message code="savegame.send"/> </button>
</form:form>
<c:url value="/" var="indexURL"/>
<a href="${indexURL}"><spring:message code="all.return"/> </a>
</body>
</html>