<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 10.11.21
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>Login</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>



<form action="controller" method="post">
    <input type="hidden" name="command" value="login"/>
    <div class="container col-lg-4">

        <h1 class="text-center">LOGIN</h1>
        <p class="text-center">Please fill in this form to sign into account.</p>
        <hr>

        <p style="color: red;">${sessionScope.errorMsg}</p>

        <div class="form-group">
            <label for="login"><b>Login</b></label>
            <input type="text" class="form-control" placeholder="Enter login" name="login" id="login" required>
        </div>

        <div class="form-group">
            <label for="password"><b>Password</b></label>
            <input type="password" class="form-control" placeholder="Enter Password" name="password" id="password" required>
        </div>

        <hr>
        <button type="submit" class="registerbtn btn-primary btn-lg">Login</button>
        <hr>

        <div class="container signin">
            <p>Don't have an account? <a href="registration.jsp">Sign up</a>.</p>
        </div>
    </div>

</form>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>

<c:remove var="errorMsg"/>

