<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 16.11.21
  Time: 01:49
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

    <title>AssigningTeacherToCourse</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div class="container mt-3">
<%--    <ul class="breadcrumb">--%>
<%--        <li class="breadcrumb-item"><a href="admin.jsp">Menu</a></li>--%>
<%--        <li class="breadcrumb-item"><a href="controller?command=adminInfoMenu">My Info</a></li>--%>
<%--        <li class="breadcrumb-item"><a href="#">Sing Out</a></li>--%>
<%--    </ul>--%>

<form method="POST" action="controller">
    <input type="hidden" name="command" value="updateCourseToTeacher"/>
<%--    <div class="container col-lg-4">--%>
        <h2 class="text-center">REGISTRATION COURSE TO TEACHER</h2>
        <p class="text-center">Please fill in this form to assign course to teacher.</p>
        <hr>

<%--    <input name="idProfile" value="${assigningTeacherCourse.idProfile}" readonly><br>--%>
        <div class="form-group">
            <label><b>Id Teacher profile</b></label>
            <input type="text" class="form-control" name="idProfile" value="${assigningTeacherCourse.idProfile}" readonly>
        </div>

<%--    <input name="profileName" value="${assigningTeacherCourse.profileName}" readonly><br>--%>
        <div class="form-group">
            <label><b>Teacher's Name</b></label>
            <input type="text" class="form-control" name="profileName" value="${assigningTeacherCourse.profileName}" readonly>
        </div>

<%--    <input name="profileSurname" value="${assigningTeacherCourse.profileSurname}" readonly><br>--%>
        <div class="form-group">
            <label><b>Teacher's surname</b></label>
            <input type="text" class="form-control" name="profileSurname" value="${assigningTeacherCourse.profileSurname}" readonly>
        </div>


<%--    <input name="idCourse" value="${assigningTeacherCourse.idCourse}" ><br>--%>
        <div class="form-group">
            <label><b>Course name</b></label>
            <select name="idCourse">
                <c:forEach var="dropdownCourseList" items="${dropdownCourseList}">
                    <option value="${dropdownCourseList.idCourse}">${dropdownCourseList.courseName}</option>
                </c:forEach>
            </select>
<%--            <input type="text" class="form-control" placeholder="Enter course Id" name="idCourse" value="${assigningTeacherCourse.idCourse}">--%>
        </div>
<%--    <input name="courseName" value="${assigningTeacherCourse.courseName}"><br>--%>


<%--    <input name="startDayCourse" value="${assigningTeacherCourse.startDayCourse}"><br>--%>
        <div class="form-group">
            <label><b>Start day course</b></label>
            <input type="date" class="form-control" placeholder="Enter start day course" name="startDayCourse" value="${assigningTeacherCourse.startDayCourse}">
        </div>

<%--    <input name="duration" value="${assigningTeacherCourse.duration}"><br>--%>

    <input type="submit" value="Save">

</form>
<hr/>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
