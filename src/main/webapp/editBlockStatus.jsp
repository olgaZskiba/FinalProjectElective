<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 17.11.21
  Time: 13:45
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
    <input type="hidden" name="command" value="updateBlockStatusProfile"/>

<%--    <div class="container col-lg-4">--%>
        <h1 class="text-center">EDIT STUDENT'S BLOCK STATUS</h1>
        <p class="text-center">Please edit information about student's block status.</p>
        <hr>
<%--    You can to change student's block status--%>
    <br>
    <br>
<%--    <input name="idProfile" value="${studentProfile.idProfile}" readonly><br>--%>
        <div class="form-group">
            <label for="idProfile"><b>Id Student(don't changed)</b></label>
            <input type="text" class="form-control" name="idProfile" id="idProfile" value="${studentProfile.idProfile}" readonly >
        </div>
<%--    <input name="name" value="${studentProfile.name}" ><br>--%>
        <div class="form-group">
            <label for="name"><b>Student's First name(don't changed)</b></label>
            <input type="text" class="form-control" name="name" id="name" value="${studentProfile.name}" readonly >
        </div>
<%--    <input name="surname" value="${studentProfile.surname}" ><br>--%>
        <div class="form-group">
            <label for="surname"><b>Student's Last name(don't changed)</b></label>
            <input type="text" class="form-control" name="surname" id="surname" value="${studentProfile.surname}" readonly >
        </div>
<%--    <input name="blockStatus" value="${studentProfile.blockStatus}" ><br>--%>
<%--        <div class="form-group">--%>
<%--            <label for="blockStatus"><b>Student's Last name(don't changed)</b></label>--%>
<%--            <input type="text" class="form-control" name="blockStatus" id="blockStatus" value="${studentProfile.blockStatus}" >--%>
<%--        </div>--%>
    <div class="form-check">
        <input type="radio" class="form-check-input" id="blockStatus" name="blockStatus" value="1" >Block
        <label class="form-check-label" for="blockStatus"></label>
    </div>
    <div class="form-check">
        <input type="radio" class="form-check-input" id="blockStatus1" name="blockStatus1" value="0" >UnBlock
        <label class="form-check-label" for="blockStatus1"></label>
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
