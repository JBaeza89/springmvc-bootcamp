<%@ tag body-content="scriptless" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
    <c:url value="/logout" var="logoutURL"/>
    <a class="nav-link" id="logout" href="${logoutURL}"><spring:message code="session.logout"/></a>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <c:url value="/login" var="loginURL"/>
    <a class="nav-link" id="login" href="${loginURL}"><spring:message code="session.login"/></a>
</sec:authorize>