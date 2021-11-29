<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 16.11.21
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>Courses List</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container mt-3">

<h3>Courses List</h3>

    <table class="table table-bordered">
        <thead>
    <tr>
        <th>Course id</th>
        <th>Course name</th>
        <th>Topic id</th>
        <th>Course duration</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
        </thead>
        <tbody>
    <c:forEach var="courseList" items="${listCourses}">
        <tr>
            <td>${courseList.idCourse}</td>
            <td>${courseList.courseName}</td>
            <td>${courseList.courseTopic}</td>
            <td>${courseList.duration}</td>
            <td>
                <a href="${pageContext.request.contextPath}/controller?command=editCourse&idCourse=${courseList.idCourse}">Edit</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/controller?command=deleteCourse&idCourse=${courseList.idCourse}">Delete</a>
            </td>

        </tr>
    </c:forEach>
        </tbody>
</table>
</div>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>