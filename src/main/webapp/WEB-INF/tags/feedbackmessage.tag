<%@ tag body-content="scriptless" %>
<%@ attribute name="message" required="false" %>
<%@ attribute name="styles" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${not empty message}">
    <span class="${styles}"><spring:message code="${message}"/> </span>
</c:if>