<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 10.11.21
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>TEACHER menu</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container mt-3">

        <p style="color: red;">${requestScope.emptyMess}</p>

<h1>TEACHER menu</h1>
<br>
<h3>All your courses</h3>
<br>
        <table class="table table-bordered">
            <thead>

    <tr>
        <th>Course id</th>
        <th>Course name</th>
        <th>Start day course</th>
        <th>Course duration</th>
        <th>Details</th>

    </tr>
            </thead>
            <tbody>
    <c:forEach var="profileCourses" items="${profileCourses}">
        <tr>
            <td>${profileCourses.idCourse}</td>
            <td>${profileCourses.courseName}</td>
            <td>${profileCourses.startDayCourse}</td>
            <td>${profileCourses.duration}</td>
            <td>
                <a href="${pageContext.request.contextPath}/controller?command=teacherMenuCoursesDetails&idCourse=${profileCourses.idCourse}">Details</a>
            </td>
<%--            <td>--%>
<%--                <a href="${pageContext.request.contextPath}/controller?command=deleteCourse&idCourse=${courseList.idCourse}">Delete</a>--%>
<%--            </td>--%>

        </tr>
    </c:forEach>
            </tbody>
</table>
</div>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
<c:remove var="emptyMess"/>