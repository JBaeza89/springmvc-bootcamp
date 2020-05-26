<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
</head>
<body>
    <a href="<c:url value='/'/>">Volver al inicio</a>
    <br><br>
    <h1>${messageError}</h1>
</body>
</html>