<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 30.11.21
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- set the locale --%>
<fmt:setLocale value="${param.locale}" scope="session"/>

<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="resources"/>

<%-- set current locale to session --%>
<c:set var="currentLocale" value="${param.locale}" scope="session"/>

<%-- goto back to the settings--%>
<%--<jsp:forward page="${sessionScope.currentPage}"/>--%>
<%--<jsp:forward page="index.jsp"/>--%>
<%--<c:redirect url="${currentAddressPage}">--%>
<jsp:forward page="${backAddress}"/>
