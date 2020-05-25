<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Juegos</title>
</head>
<body>
<h1>Listado de Juegos</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Nombre del Juego</th>
    </tr>
    <c:forEach var = "game"
               items = "${gameList}">
        <tr>
            <td>${game.id}</td>
            <td>${game.title}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>