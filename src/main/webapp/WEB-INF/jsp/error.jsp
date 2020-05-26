<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
</head>
<body>
    <c:url value="/" var="indexURL"/>
    <a href="${indexURL}">Volver al inicio</a>
    <br><br>
    <h1>Problemas con la URL introducida</h1>
    <h3>${messageError}</h3>
</body>
</html>