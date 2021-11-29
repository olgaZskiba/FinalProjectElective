<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 24.11.21
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>Edit Grade</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container mt-3">

        <form method="POST" action="controller">
            <input type="hidden" name="command" value="teacherMenuApproveUpdateGrade"/>

            <h1 class="text-center">EDIT STUDENT'S GRADE</h1>
            <p class="text-center">Please edit information about student's grade.</p>
            <hr>
            <br>
            <br>

            <div class="form-group">
                <label for="idCourse"><b>Id Course(don't changed)</b></label>
                <input type="text" class="form-control" name="idCourse" id="idCourse" value="${gradeBook.idCourse}" readonly >
            </div>
            <div class="form-group">
                <label for="idStudent"><b>Id Student(don't changed)</b></label>
                <input type="text" class="form-control" name="idStudent" id="idStudent" value="${gradeBook.idStudent}" readonly >
            </div>
            <div class="form-group">
                <label for="nameStudent"><b>Student First name(don't changed)</b></label>
                <input type="text" class="form-control" name="nameStudent" id="nameStudent" value="${gradeBook.nameStudent}" readonly >
            </div>
            <div class="form-group">
                <label for="surnameStudent"><b>Student Last name(don't changed)</b></label>
                <input type="text" class="form-control" name="surnameStudent" id="surnameStudent" value="${gradeBook.surnameStudent}" readonly >
            </div>
            <div class="form-group">
                <label for="grade"><b>Student Grade</b></label>
                <input type="text" class="form-control" name="grade" id="grade" value="${gradeBook.grade}">
            </div>
            <hr>
            <button type="submit" class="registerbtn btn-primary btn-lg">Save</button>
            <hr>
</form>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
