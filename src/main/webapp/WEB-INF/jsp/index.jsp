<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Juegos</title>
    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/index.js"></script>
</head>
<body>
<h1>Listado de Juegos</h1>

<tag:feedbackmessage message="${deleteMessage}"/>
<br>
<c:url value="/game/new" var="newGameURL"/>
<a href="${newGameURL}">Nuevo juego</a>
<c:url value="/game" var="queryURL"/>
<form action="${queryURL}" method="get">
<input type="text" name="q" value="${q}" placeholder="Buscar juegos">
<button type="submit">Buscar</button>
</form>
<table>
    <tr>
        <th>ID</th>
        <th>Nombre del Juego</th>
    </tr>
    <c:forEach var = "game"
               items = "${gameList}">
        <c:url value="/game/${game.id}" var="gameURL"/>
        <c:url value="/game/delete/${game.id}" var="deleteURL"/>
        <c:url value="/game/edit/${game.id}" var="editURL"/>
        <tr>
            <td><a href="${gameURL}">${game.id}</a></td>
            <td><a href="${gameURL}">${game.title}</a></td>
            <td><button onclick="deleteById('${deleteURL}', '${game.title}')">Eliminar</button></td>
            <td><a href="${editURL}">Editar</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>