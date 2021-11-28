<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>Create course form</title>
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
<h3>Create course form</h3>
<hr/>
<form name="createCourseForm" method="POST" action="controller">
    <input type="hidden" name="command" value="createCourse"/>

<%--    <div class="container col-lg-4">--%>
        <h2 class="text-center">REGISTRATION COURSE</h2>
        <p class="text-center">Please fill in this form to create course.</p>
        <hr>

        <div class="form-group">
            <label for="courseName"><b>Course Name</b></label>
            <input type="text" class="form-control" placeholder="Enter course name" name="courseName" id="courseName" required>
        </div>

        <div class="form-group">
            <label for="courseTopic"><b>Course Topic</b></label>
            <input type="text" class="form-control" placeholder="Enter course topic" name="courseTopic" id="courseTopic" required>
        </div>

        <div class="form-group">
            <label for="duration"><b>Course Duration</b></label>
            <input type="text" class="form-control" placeholder="Enter course duration" name="duration" id="duration" required>
        </div>
<%--    Course Topic:<br/>--%>
<%--    <input type="text" name="courseTopic" value=""><br/>--%>

<%--    Course Duration:<br/>--%>
<%--    <input type="text" name="duration" value=""><br/>--%>

        <hr>
        <button type="submit" class="btn btn-lg btn-primary btn-block">Register</button>

<%--    <input type="submit" value="Enter">--%>
<%--    </div>--%>
</form>
<hr/>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>