<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 12.11.21
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/"><fmt:message key="_menu.jsp_Home"/> &nbsp; &nbsp; </a>
                </li>

                <c:if test="${sessionScope.profileRole == null}">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="index_jsp.login"/></a>
                    </li>
                </c:if>


                <c:if test="${sessionScope.profileRole == 'ADMIN'}">
                    <li class="nav-item">
                        <a class="navbar-brand" href="admin.jsp">
                            <img src="img_avatar1.png" alt="Avatar Logo" style="width:40px;" class="rounded-pill">
                        </a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.profileRole == 'TEACHER'}">
                    <li class="nav-item">
                        <a class="navbar-brand" href="controller?command=coursesForTeacherMenu">
                            <img src="img_avatar1.png" alt="Avatar Logo" style="width:40px;" class="rounded-pill">
                        </a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.profileRole == 'STUDENT'}">
                    <li class="nav-item">
                        <a class="navbar-brand" href="controller?command=studentMainMenu">
                            <img src="img_avatar1.png" alt="Avatar Logo" style="width:40px;" class="rounded-pill">
                        </a>
                    </li>
                </c:if>


                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"><fmt:message key="index_jsp.topic_list"/></a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=topicList&page=1&pageSize=6"><fmt:message key="_menu_jsp.all_topics"/></a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=topicList&topic=java&page=1&pageSize=4">JAVA</a>
                        </li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=topicList&topic=.net&page=1&pageSize=4">.NET</a>
                        </li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=topicList&topic=cPlus&page=1&pageSize=4">C++</a>
                        </li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=topicList&topic=javaScript&page=1&pageSize=4">Java
                            Script</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"><fmt:message key="_menu_jsp.courses_info"/></a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=commonMainMenu&page=1&pageSize=6"><fmt:message key="_menu_jsp.available_courses"/></a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=sortCoursesByAtoZ&page=1&pageSize=4"><fmt:message key="_menu_jsp.sort_courses_a_z"/></a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=sortCoursesByZtoA&page=1&pageSize=4"><fmt:message key="_menu_jsp.sort_courses_z_a"/></a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=sortCoursesByDuration&page=1&pageSize=4"><fmt:message key="_menu_jsp.sort_courses_by_duration"/></a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=sortCoursesByStudentsCount&page=1&pageSize=4"><fmt:message key="_menu_jsp.sort_courses_by_students"/></a></li>
                    </ul>
                </li>

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/controller?command=teachersListMainMenu&page=1&pageSize=3"><fmt:message key="index_jsp.teacher_list"/></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%--<div class="text-black text-lg-end">--%>
<%--<form action="changeLocale.jsp" method="post">--%>
<%--    <fmt:message key="nav_jsp.label.set_locale"/>:--%>

<%--    <select name="locale">--%>
<%--        <c:forEach items="${applicationScope.locales}" var="locale">--%>
<%--            <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>--%>
<%--            <option value="${locale.key}" ${selected}>${locale.value}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>

<%--    <input type="submit" value="<fmt:message key='nav_jsp.form.submit_save_locale'/>">--%>

<%--</form>--%>
<%--</div>--%>

<c:if test="${sessionScope.profileRole == 'ADMIN'}">
    <div class="container mt-3">
    <ul class="breadcrumb">
        <li class="breadcrumb-item"><a href="admin.jsp">Menu</a></li>
        <li class="breadcrumb-item"><a href="controller?command=adminInfoMenu">My Info</a></li>
        <li class="breadcrumb-item"><a href="controller?command=logout">LogOut</a></li>
    </ul>
        <div class="text-black text-lg-end">
            Date: <tf:tagfile/>
        </div>
    </div>
</c:if>

<c:if test="${sessionScope.profileRole == 'TEACHER'}">
    <div class="container mt-3">
        <ul class="breadcrumb">
            <li class="breadcrumb-item"><a href="controller?command=coursesForTeacherMenu">Menu</a></li>
            <li class="breadcrumb-item"><a href="controller?command=teacherInfoMenu">My Info</a></li>
            <li class="breadcrumb-item"><a href="controller?command=logout">LogOut</a></li>
        </ul>
    </div>
</c:if>

<c:if test="${sessionScope.profileRole == 'STUDENT'}">
    <div class="container mt-3">
        <ul class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/controller?command=studentMainMenu">My Info</a></li>
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/controller?command=studentMenuFutureCourses&idProfile=${student.idProfile}">My courses</a></li>
            <li class="breadcrumb-item"><a href="controller?command=logout">LogOut</a></li>
        </ul>
    </div>
</c:if>

