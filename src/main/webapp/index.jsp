<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="backAddress" value="${pageContext.request.servletPath}" scope="session"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title><fmt:message key="index_jsp.Home_Page"/></title>
</head>

<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container-fluid">
<%--<h3>Home Page </h3>--%>
    <h3><fmt:message key="index_jsp.Home_Page"/></h3>

<%--This is demo Simple web application using jsp,servlet &amp; Jdbc. --%>
<fmt:message key="index_jsp.simple_web_application"/>
    <br>
    <br>
<%--    It includes the following functions:--%>
<b><fmt:message key="index_jsp.what_include_application"/></b>
<ul>
    <li><fmt:message key="index_jsp.login"/></li>
    <li><fmt:message key="index_jsp.course_list"/></li>
    <li><fmt:message key="index_jsp.topic_list"/></li>
    <li><fmt:message key="index_jsp.teacher_list"/></li>
    <li><fmt:message key="index_jsp.admin_menu"/></li>
    <li><fmt:message key="index_jsp.teacher_menu"/></li>
    <li><fmt:message key="index_jsp.student_menu"/></li>
</ul>

    <div class="text-black text-lg-end">
        <form action="changeLocale.jsp" method="post">
            <fmt:message key="nav_jsp.label.set_locale"/>:

            <select name="locale">
                <c:forEach items="${applicationScope.locales}" var="locale">
                    <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                    <option value="${locale.key}" ${selected}>${locale.value}</option>
                </c:forEach>
            </select>

            <input type="submit" value="<fmt:message key='nav_jsp.form.submit_save_locale'/>">

        </form>
    </div>

</div>



<jsp:include page="_footer.jsp"></jsp:include>


</body>
</html>
