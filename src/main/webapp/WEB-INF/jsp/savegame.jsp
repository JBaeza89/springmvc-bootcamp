<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code='${title}'/></title>
    <tag:dependencies/>
    <script src="/js/index.js"></script>
</head>
<body <tag:classbody/>>
<tag:loginandreturn/>
<h1><spring:message code='${title}'/></h1>
<c:url value="${not empty game.id ? '/game/edit/'.concat(game.id) : '/game/new'}" var="formURL"/>
<form:form action="${formURL}" method="post" modelAttribute="game">
    <div class="form-group">
        <label for="title"><spring:message code="all.title"/></label>
        <form:input path="title" cssClass="form-control" id="title"/>
        <form:errors path="title" class="errorMessage"/>
    </div>
    <div class="form-group">
        <label for="steam"><spring:message code="all.steamid"/></label>
        <form:input path="steamId" cssClass="form-control" id="steam"/>
    </div>
    <div class="form-group">
        <label for="description"><spring:message code="all.description"/></label>
        <form:textarea path="description" class="form-control" rows="5" id="description"/>
    </div>
    <button class="btn btn-primary" type="submit"><spring:message code="savegame.send"/> </button>
</form:form>
</body>
</html>