<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:if test="${deleteMessage != null}">
    <span class="bg-yellow">${deleteMessage}</span>
</c:if>
<table>
    <tr>
        <th>ID</th>
        <th>Nombre del Juego</th>
    </tr>
    <c:forEach var = "game"
               items = "${gameList}">
        <c:url value="/game/${game.id}" var="gameURL"/>
        <c:url value="/game/delete/${game.id}" var="deleteURL"/>
        <tr>
            <td><a href="${gameURL}">${game.id}</a></td>
            <td><a href="${gameURL}">${game.title}</a></td>
            <td><button onclick="deleteById('${deleteURL}', '${game.title}')">Eliminar</button></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>