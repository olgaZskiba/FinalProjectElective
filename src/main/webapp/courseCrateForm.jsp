<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

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

<h3>Create course form</h3>
<hr/>
<form name="createCourseForm" method="POST" action="controller"  enctype="multipart/form-data">
    <input type="hidden" name="command" value="createCourse"/>

    <p style="color: red;">${sessionScope.errMess}</p>

        <h2 class="text-center">REGISTRATION COURSE</h2>
        <p class="text-center">Please fill in this form to create course.</p>
        <hr>

        <div class="form-group">
            <label for="courseName"><b>Course Name</b></label>
            <input type="text" class="form-control" placeholder="Enter course name" name="courseName" id="courseName" required>
        </div>


    <sql:query var="rs" dataSource="jdbc/ConnectPull">
        SELECT * FROM topic ORDER BY topic_id
    </sql:query>

    <div class="form-group">
        <label><b>Course Topic</b></label>
        <select name="courseTopic">
            <c:forEach var="row" items="${rs.rows}">
               <option value="${row.topic_id}">${row.name}</option>
            </c:forEach>
        </select>
    </div>


<%--    <div class="form-group">--%>
<%--            <label for="courseTopic"><b>Course Topic</b></label>--%>
<%--            <input type="text" class="form-control" placeholder="Enter course topic" name="courseTopic" id="courseTopic" required>--%>
<%--        </div>--%>

    <div class="form-group">
        <label for="courseInfo"><b>Course Info</b></label>
        <input type="text" class="form-control" placeholder="Enter course name" name="courseInfo" id="courseInfo" required>
    </div>


<%--    <form action="controller" method="post" enctype="multipart/form-data">--%>
<%--        <input type="hidden" name="command" value="createCourseUploadImg"/>--%>
    <tr>
        <td>Photo for course:</td>
        <input type="file" name="file" />
    </tr>
<%--        <input type="submit" value="Upload" />--%>
<%--    </form>--%>


    <div class="form-group">
            <label for="duration"><b>Course Duration</b></label>
            <input type="text" class="form-control" placeholder="Enter course duration" name="duration" id="duration" required>
        </div>

        <hr>
        <button type="submit" class="btn btn-lg btn-primary btn-block">Register</button>

</form>
<hr/>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
<c:remove var="errMess"/>