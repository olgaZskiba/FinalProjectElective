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
    <%--<div class="container-fluid">--%>
<%--    <ul class="breadcrumb">--%>
<%--        <li class="breadcrumb-item"><a href="admin.jsp">Menu</a></li>--%>
<%--        <li class="breadcrumb-item"><a href="controller?command=adminInfoMenu">My Info</a></li>--%>
<%--        <li class="breadcrumb-item"><a href="#">Sing Out</a></li>--%>
<%--    </ul>--%>
<form method="POST" action="controller">
    <input type="hidden" name="command" value="updateCourse"/>

<%--    <div class="container col-lg-4">--%>
        <h1 class="text-center">EDIT COURSE</h1>
        <p class="text-center">Please edit information about course.</p>
        <hr>
<%--    <input name="idCourse" value="${courseForEdit.idCourse}" readonly><br>--%>
        <div class="form-group">
            <label for="idCourse"><b>Id Course(don't changed)</b></label>
            <input type="text" class="form-control" name="idCourse" id="idCourse" value="${courseForEdit.idCourse}" readonly >
        </div>
<%--    <input name="courseName" value="${courseForEdit.courseName}" ><br>--%>
        <div class="form-group">
            <label for="courseName"><b>Course Name</b></label>
            <input type="text" class="form-control" name="courseName" id="courseName" value="${courseForEdit.courseName}">
        </div>
<%--    <input name="courseTopic" value="${courseForEdit.courseTopic}" ><br>--%>
        <div class="form-group">
            <label for="courseTopic"><b>Course Topic</b></label>
            <input type="text" class="form-control" name="courseTopic" id="courseTopic" value="${courseForEdit.courseTopic}">
        </div>
<%--    <input name="duration" value="${courseForEdit.duration}" ><br>--%>
        <div class="form-group">
            <label for="duration"><b>Course Duration</b></label>
            <input type="text" class="form-control" name="duration" id="duration" value="${courseForEdit.duration}">
        </div>

<%--    <input type="submit" value="Save">--%>
        <hr>
        <button type="submit" class="registerbtn btn-primary btn-lg">Save</button>
        <hr>
    </div>
</form>
        <jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
