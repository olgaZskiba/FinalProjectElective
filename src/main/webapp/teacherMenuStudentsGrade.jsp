<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 18.11.21
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>TEACHER menu</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container mt-3">

<br>
<h3>Students and they grades</h3>
<br>
    <table class="table table-bordered">
        <thead>

    <tr>
        <th>Student id</th>
        <th>Student name</th>
        <th>Student surname</th>
        <th>Course id</th>
        <th>Course name</th>
        <th>Start day course</th>
        <th>Student grade</th>
        <th>Edit Grade</th>
    </tr>
        </thead>
        <tbody>
    <c:forEach var="gradeBookList" items="${gradeBookList}">
        <tr>
            <td>${gradeBookList.idStudent}</td>
            <td>${gradeBookList.nameStudent}</td>
            <td>${gradeBookList.surnameStudent}</td>
            <td>${gradeBookList.idCourse}</td>
            <td>${gradeBookList.courseName}</td>
            <td>${gradeBookList.startDayCourse}</td>
            <td>${gradeBookList.grade}</td>

            <td>
                <a href="${pageContext.request.contextPath}/controller?command=teacherMenuUpdateGrade&idStudent=${gradeBookList.idStudent}&idCourse=${gradeBookList.idCourse}">Edit</a>
            </td>
                <%--            <td>--%>
                <%--                <a href="${pageContext.request.contextPath}/controller?command=deleteCourse&idCourse=${courseList.idCourse}">Delete</a>--%>
                <%--            </td>--%>

        </tr>
    </c:forEach>
        </tbody>
</table>
<%--</form>--%>
</div>
<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>

