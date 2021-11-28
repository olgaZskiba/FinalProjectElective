<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 12.11.21
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Registration</title>--%>
<%--</head>--%>
<%--<body><h3>Registration</h3>--%>
<%--<hr/>--%>
<%--<form name="registrationForm" method="POST" action="controller">--%>
<%--    <input type="hidden" name="command" value="registration"/>--%>

<%--    Login:<br/>--%>
<%--    <input type="text" name="login" value=""><br/>--%>

<%--    Password:<br/>--%>
<%--    <input type="password" name="password" value=""><br/>--%>

<%--    Email:<br/>--%>
<%--    <input type="email" name="email" value=""><br/>--%>

<%--    Telephone:<br/>--%>
<%--    <input type="tel" name="telephone" value=""><br/>--%>

<%--    Name:<br/>--%>
<%--    <input type="text" name="name" value=""><br/>--%>

<%--    Surname:<br/>--%>
<%--    <input type="text" name="surname" value=""><br/>--%>

<%--    <input type="submit" value="Enter">--%>

<%--</form>--%>
<%--<hr/>--%>
<%--</body>--%>
<%--</html>--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>Registration of User</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<form action="controller" method="post">
    <input type="hidden" name="command" value="registration"/>
    <div class="container col-lg-4">

        <p style="color: red;">${sessionScope.commonMess}</p>

        <h2 class="text-center">REGISTRATION</h2>
        <p class="text-center">Please fill in this form to create an account.</p>
        <hr>

        <p style="color: red;">${sessionScope.loginMess}</p>
        <div class="form-group">
            <label for="login"><b>Login</b></label>
            <input type="text" class="form-control" placeholder="Enter login" name="login" id="login" required>
        </div>

    <p style="color: red;">${sessionScope.passwordMess}</p>
        <div class="form-group">
            <label for="password"><b>Password</b></label>
            <input type="password" class="form-control" placeholder="Enter password" name="password" id="password" required>
        </div>

    <p style="color: red;">${sessionScope.emailMess}</p>
        <div class="form-group">
            <label for="email"><b>Email</b></label>
            <input type="text" class="form-control" placeholder="Enter email" name="email" id="email" required>
        </div>

    <p style="color: red;">${sessionScope.telMess}</p>
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

        <hr>
        <button type="submit" class="btn btn-lg btn-primary btn-block">Register</button>

        <div class="container signin">
            <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
        </div>
    </div>
</form>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
