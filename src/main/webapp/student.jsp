<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 10.11.21
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>STUDENT menu</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<div class="container mt-3">
    <%--<div class="container-fluid">--%>
<%--    <ul class="breadcrumb">--%>
<%--        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/controller?command=studentMainMenu">My Info</a></li>--%>
<%--        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/controller?command=studentMenuFutureCourses&idProfile=${student.idProfile}">My courses</a></li>--%>
<%--        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/index.jsp">Common main menu</a></li>--%>
<%--        <li class="breadcrumb-item"><a href="#">Sing Out</a></li>--%>
<%--    </ul>--%>

<h1>STUDENT menu</h1>

<%--<div style="padding: 5px;">--%>

<%--    <a href="${pageContext.request.contextPath}/controller?command=studentMainMenu">Home</a>--%>
<%--    |--%>
<%--    <a href="${pageContext.request.contextPath}/controller?command=studentMenuFutureCourses&idProfile=${student.idProfile}">List of future courses</a>--%>
<%--    |--%>
<%--    <a href="${pageContext.request.contextPath}/controller?command=studentMenuCurrentCourses&idProfile=${student.idProfile}">List of current courses</a>--%>
<%--    |--%>
<%--    <a href="${pageContext.request.contextPath}/controller?command=studentMenuPastCourses&idProfile=${student.idProfile}">List of past courses</a>--%>


    <h3>Your info</h3>
        <br>
        <br>
    <tr>Id: <td>${student.idProfile}</td>
        <br>
        <br>
        Login: <td>${student.login}</td>
        <br>
        <br>
        Email: <td>${student.email}</td>
        <br>
        <br>
        Telephone: <td>${student.telephone}</td>
        <br>
        <br>
        Name: <td>${student.name}</td>
        <br>
        <br>
        Surname: <td>${student.surname}</td>
        <br>
        <br>
        Block status: <td>${student.blockStatus}</td>
        <br>
    </tr>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
