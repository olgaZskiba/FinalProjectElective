<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 10.11.21
  Time: 14:56
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

    <title>Admin menu</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container mt-3">
    <%--<div class="container-fluid">--%>
<%--    <ul class="breadcrumb">--%>
<%--        <li class="breadcrumb-item"><a href="admin.jsp">Menu</a></li>--%>
<%--        <li class="breadcrumb-item"><a href="controller?command=adminInfoMenu">My Info</a></li>--%>
<%--        <li class="breadcrumb-item"><a href="controller?command=logout">Sing Out</a></li>--%>
<%--    </ul>--%>
        <p><h3>ADMIN menu</h3></p>

        <br>
        <li>Teacher menu</li>
        <br/>
        <a href="${pageContext.request.contextPath}/registerTeacher.jsp">Register form for new teacher</a>
        <br>
        <br>
        <%--<a href="${pageContext.request.contextPath}/teachersForAdminMenu">Assigning a course to a teacher</a>--%>
        <a href="${pageContext.request.contextPath}/controller?command=teachersForAdminMenu">Assigning a course to a
            teacher</a>
        <br>
        <br>
        <li>Course menu</li>
        <br/>
        <a href="${pageContext.request.contextPath}/courseCrateForm.jsp">Create new course</a>
        <br>
        <br>
        <a href="${pageContext.request.contextPath}/controller?command=allCourses">Edit course menu</a>
        <br>
        <br>
        <li>Student menu</li>
        <br/>
        <a href="${pageContext.request.contextPath}/controller?command=allStudents">Edit block status for student</a>
        <br>

</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
