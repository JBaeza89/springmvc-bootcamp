<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="index.title" /></title>
    <tag:dependencies/>
    <script src="/js/index.js"></script>
</head>
<body <tag:classbody/>>
<h1><spring:message code="index.title" /></h1>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <tag:loginlogout/>
            </li>
            <sec:authorize access="isAuthenticated()">
            <li class="nav-item">
                <c:url value="/game/new" var="newGameURL"/>
                <a class="nav-link" href="${newGameURL}"><spring:message code="index.newgame" /></a>
            </li>
            </sec:authorize>
        </ul>
        <c:url value="/game" var="queryURL"/>
        <form class="form-inline my-2 my-lg-0" action="${queryURL}" method="get">
            <input class="form-control mr-sm-2" type="search" name="q" value="${q}" placeholder="<spring:message code="index.placeholder"/>" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><spring:message code="index.button"/></button>
        </form>
    </div>
</nav>
<tag:feedbackmessage message="${deleteMessage}"/>
<div class="container bg-light text-dark">
    <div class="row bg-primary text-light">
        <div class="col-sm-2">ID</div>
        <div class="col-sm-5"><spring:message code="index.gametitle"/></div>
    </div>
    <c:forEach var = "game"
               items = "${gameList}">
        <c:url value="/game/${game.id}" var="gameURL"/>
        <c:url value="/game/delete/${game.id}" var="deleteURL"/>
        <c:url value="/game/edit/${game.id}" var="editURL"/>
        <div class="row">
            <div class="col-sm-2">
                <sec:authorize access="isAuthenticated()"><a href="${gameURL}"></sec:authorize>
                    ${game.id}
                <sec:authorize access="isAuthenticated()"></a></sec:authorize>
            </div>
            <div class="col-sm-5">
                <sec:authorize access="isAuthenticated()"><a href="${gameURL}"></sec:authorize>
                    ${game.title}
                <sec:authorize access="isAuthenticated()"></a></sec:authorize>
            </div>
            <sec:authorize access="isAuthenticated()">
                <spring:message code="js.delete.confirm" arguments="${game.title}" var="deleteConfirm"/>
                <div class="col-sm-1">
                    <button class="btn btn-danger" onclick="deleteById('${deleteURL}', '${deleteConfirm}')"><spring:message code="index.delete"/></button></td>
                </div>
                <div class="col-sm-1">
                    <a class="btn btn-info" role="button" href="${editURL}"><spring:message code="index.edit"/></a>
                </div>
            </sec:authorize>
        </div>
    </c:forEach>
</div>
</body>
</html>