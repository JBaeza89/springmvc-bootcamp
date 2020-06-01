<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${game.title}</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

    <c:if test="${saveMessage ne null}">
        <span class="bg-yellow">${saveMessage}</span>
    </c:if>
    <p>Id: ${game.id}</p>
    <p>Titulo: ${game.title}</p>
    <p>Descripcion: ${game.description}</p>
    <p>Steam_ID: ${game.steamId}</p>
    <c:url value="/" var="indexURL"/>
    <a href="${indexURL}">Volver al inicio</a>
</body>
</html>