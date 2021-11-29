<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 14.11.21
  Time: 01:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head><meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>Registration form for Teacher</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container mt-3">

<h3>Registration form for Teacher</h3>
<hr/>
<form name="registrationTeacherForm" method="POST" action="controller">
    <input type="hidden" name="command" value="registrationTeacher"/>

    <p style="color: red;">${sessionScope.ErrorMes}</p>

            <h2 class="text-center">REGISTRATION TEACHER</h2>
            <p class="text-center">Please fill in this form to create teacher account.</p>
            <hr>

            <div class="form-group">
                <label for="login"><b>Login</b></label>
                <input type="text" class="form-control" placeholder="Enter login" name="login" id="login" required>
            </div>

            <div class="form-group">
                <label for="password"><b>Password</b></label>
                <input type="password" class="form-control" placeholder="Enter password" name="password" id="password" required>
            </div>

            <div class="form-group">
                <label for="email"><b>Email</b></label>
                <input type="text" class="form-control" placeholder="Enter email" name="email" id="email" required>
            </div>

            <div class="form-group">
                <label for="telephone"><b>Telephone</b></label>
                <input type="text" class="form-control" placeholder="Enter telephone" name="telephone" id="telephone" required>
            </div>

            <div class="form-group">
                <label for="name"><b>First Name</b></label>
                <input type="text" class="form-control" placeholder="Enter First Name" name="name" id="name" required>
            </div>

            <div class="form-group">
                <label for="surname"><b>Last Name</b></label>
                <input type="text" class="form-control" placeholder="Enter Last Name" name="surname" id="surname" required>
            </div>

            <div class="form-group">
                <label for="role"><b>Role</b></label>
                <input type="text" class="form-control" placeholder="Enter role" name="role" id="role" required>
            </div>

            <hr>
            <button type="submit" class="btn btn-lg btn-primary btn-block">Register</button>

    </form>

<hr/>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
<c:remove var="ErrorMes"/>