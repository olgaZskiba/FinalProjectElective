<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 17.11.21
  Time: 09:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>Title</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container mt-3">

<form name="idCourse" method="POST" action="controller">
    <input type="hidden" name="command" value="approveDeleteCourse"/>


        <h4 class="text-center">Do you want to delete this course?</h4>

        <table class="table table-bordered">
            <thead>
        <tr>
            <th>Course id</th>
            <th>Course name</th>
            <th>Topic id</th>
            <th>Course duration</th>
            <th>Delete</th>
        </tr>
            </thead>
            <tbody></tbody>
            <tr>
                <td><input name="idCourse" value="${deleteCourse.idCourse}" readonly><br></td>
                <td>${deleteCourse.courseName}</td>
                <td>${deleteCourse.courseTopic}</td>
                <td>${deleteCourse.duration}</td>
                <td>
                        <input type="submit" value="Delete">

                </td>
            </tr>
            </tbody>
    </table>
</form>
</div>
        <jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
