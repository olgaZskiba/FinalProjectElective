<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 17.11.21
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<div class="container mt-3">

<h3>Student List for set or remove block status</h3>

  <table class="table table-bordered">
    <thead>

  <tr>
    <th>Student id</th>
    <th>Student name</th>
    <th>Student surname</th>
    <th>Student email</th>
    <th>Student telephone</th>
    <th>Student role</th>
    <th>Student block status</th>
    <th>Edit</th>
  </tr>
    </thead>
    <tbody>
  <c:forEach var="studentsList" items="${studentsList}">
    <tr>
      <td>${studentsList.idProfile}</td>
      <td>${studentsList.name}</td>
      <td>${studentsList.surname}</td>
      <td>${studentsList.email}</td>
      <td>${studentsList.telephone}</td>
      <td>${studentsList.role}</td>
      <td>${studentsList.blockStringStatus}</td>
      <td>
        <a href="${pageContext.request.contextPath}/controller?command=editStudentBlockStatus&idProfile=${studentsList.idProfile}">Edit</a>
      </td>

    </tr>
  </c:forEach>
    </tbody>
</table>
</div>


<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
