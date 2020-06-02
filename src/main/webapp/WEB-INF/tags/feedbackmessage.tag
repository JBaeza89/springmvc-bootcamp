<%@ tag body-content="scriptless" %>
<%@ attribute name="message" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty message}">
    <span class="bg-yellow">${message}</span>
</c:if>