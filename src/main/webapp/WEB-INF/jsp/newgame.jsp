<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<h1>Agregar Juego</h1>
<form:form action="/game/new" method="post" modelAttribute="game">
    Titulo: <form:input path="title"/><br>
    Steam_Id: <form:input path="steamId"/><br>
    Descripcion: <form:textarea path="description"/><br>
    <button type="submit">Enviar</button>
</form:form>
</body>
</html>