<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 20.11.21
  Time: 00:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>List of future courses</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container mt-3">

    <h3>List of future courses</h3>

    <table class="table table-bordered">
        <thead>

        <tr>
            <th>Course id</th>
            <th>Course name</th>
            <th>Start day course</th>
            <th>Course duration</th>
            <th>Course grade</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="profileCourseList" items="${profileCourseList}">
            <tr>
                <td>${profileCourseList.idCourse}</td>
                <td>${profileCourseList.courseName}</td>
                <td>${profileCourseList.startDayCourse}</td>
                <td>${profileCourseList.duration}</td>
                <td>${profileCourseList.grade}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    <hr>

    <h3>List of current courses</h3>

        <table class="table table-bordered">
            <thead>

        <tr>
            <th>Course id</th>
            <th>Course name</th>
            <th>Start day course</th>
            <th>Course duration</th>
            <th>Course grade</th>
        </tr>
            </thead>
            <tbody>
        <c:forEach var="currentCoursesList" items="${currentCoursesList}">
            <tr>
                <td>${currentCoursesList.idCourse}</td>
                <td>${currentCoursesList.courseName}</td>
                <td>${currentCoursesList.startDayCourse}</td>
                <td>${currentCoursesList.duration}</td>
                <td>${currentCoursesList.grade}</td>
            </tr>
        </c:forEach>
            </tbody>
    </table>
    <hr>
        <hr>
    <h3>List of past courses and grades</h3>
        <table class="table table-bordered">
            <thead>

        <tr>
            <th>Course id</th>
            <th>Course name</th>
            <th>Start day course</th>
            <th>Course duration</th>
            <th>Course grade</th>
        </tr>
            </thead>
            <tbody>
        <c:forEach var="pastCoursesList" items="${pastCoursesList}">
            <tr>
                <td>${pastCoursesList.idCourse}</td>
                <td>${pastCoursesList.courseName}</td>
                <td>${pastCoursesList.startDayCourse}</td>
                <td>${pastCoursesList.duration}</td>
                <td>${pastCoursesList.grade}</td>
            </tr>
        </c:forEach>
            </tbody>
    </table>
</div>
    <jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
