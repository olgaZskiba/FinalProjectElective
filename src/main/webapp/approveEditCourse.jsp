<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 17.11.21
  Time: 00:28
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

    <title>Title</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div class="container mt-3">

<form method="POST" action="controller" enctype="multipart/form-data">
    <input type="hidden" name="command" value="updateCourse"/>


        <h1 class="text-center">EDIT COURSE</h1>
        <p class="text-center">Please edit information about course.</p>
        <hr>

        <div class="form-group">
            <label for="idCourse"><b>Id Course(don't changed)</b></label>
            <input type="text" class="form-control" name="idCourse" id="idCourse" value="${courseForEdit.idCourse}" readonly >
        </div>

        <div class="form-group">
            <label for="courseName"><b>Course Name</b></label>
            <input type="text" class="form-control" name="courseName" id="courseName" value="${courseForEdit.courseName}">
        </div>

        <div class="form-group">
            <label for="courseTopic"><b>Course Topic</b></label>
            <input type="text" class="form-control" name="courseTopic" id="courseTopic" value="${courseForEdit.courseTopic}">
        </div>

        <div class="form-group">
            <label for="duration"><b>Course Duration</b></label>
            <input type="text" class="form-control" name="duration" id="duration" value="${courseForEdit.duration}">
        </div>

    <img src="data:image/jpg;base64,${courseForEdit.base64Image}" width="240" height="300"/>

    <tr>
        <b>Change photo for course: </b>
        <input type="file" name="file" />
    </tr>

    <div class="form-group">
        <label for="infoCourse"><b>Course Info</b></label>
        <input type="text" class="form-control" name="infoCourse" id="infoCourse" value="${courseForEdit.infoCourse}">
    </div>

        <hr>
        <button type="submit" class="registerbtn btn-primary btn-lg">Save</button>
        <hr>
</form>
</div>
        <jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
