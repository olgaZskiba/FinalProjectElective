<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 12.11.21
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/">Home &nbsp; &nbsp; </a>
                </li>

                <c:if test="${sessionScope.profileRole == null}">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/login.jsp">Login</a>
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
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Topic List</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=topicList&page=1&pageSize=6">All
                            topics</a></li>
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
                <%--            <li class="nav-item">--%>
                <%--                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=topicList&page=1&pageSize=3">Topic List</a>--%>
                <%--            </li>--%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Courses
                        Info</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=commonMainMenu&page=1&pageSize=6">All
                            courses</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=sortCoursesByAtoZ&page=1&pageSize=4">Sort
                            courses A->Z</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=sortCoursesByZtoA&page=1&pageSize=4">Sort
                            courses Z->A</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=sortCoursesByDuration&page=1&pageSize=4">Sort
                            courses by Duration</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=sortCoursesByStudentsCount&page=1&pageSize=4">Sort
                            courses by Students</a></li>
                    </ul>
                </li>
                <%--            <li class="nav-item">--%>
                <%--                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=commonMainMenu&page=1&pageSize=6">Courses Info</a>--%>
                <%--            </li>--%>

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/controller?command=teachersListMainMenu&page=1&pageSize=3">Teacher
                        List</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<c:if test="${sessionScope.profileRole == 'ADMIN'}">
    <div class="container mt-3">
    <ul class="breadcrumb">
        <li class="breadcrumb-item"><a href="admin.jsp">Menu</a></li>
        <li class="breadcrumb-item"><a href="controller?command=adminInfoMenu">My Info</a></li>
        <li class="breadcrumb-item"><a href="controller?command=logout">LogOut</a></li>
    </ul>
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

