<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 14.11.21
  Time: 02:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
    <meta charset="UTF-8">
    <title>Teachers List</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container mt-3">

<h3>Teachers List for assigning courses on teacher</h3>

<table class="table table-bordered">
    <thead>

    <tr>
        <th>Teacher Name</th>
        <th>Teacher Surname</th>
        <th>Course name</th>
        <th>Start day course</th>
        <th>Course duration</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="courses" items="${coursesList}">
        <tr>
            <td>${courses.profileName}</td>
            <td>${courses.profileSurname}</td>
            <td>${courses.courseName}</td>
            <td>${courses.startDayCourse}</td>
            <td>${courses.duration}</td>
            <td>
                <a href="${pageContext.request.contextPath}/controller?command=editTeacherForCourse&idProfile=${courses.idProfile}&idCourse=${courses.idCourse}">Edit</a>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</div>


<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
