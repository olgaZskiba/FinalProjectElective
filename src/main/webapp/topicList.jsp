
<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 12.11.21
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>Topic List</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

Page ${page} of ${pageCount}

|
<c:choose>
    <c:when test="${topic==null}">

<c:choose>
    <c:when test="${page - 1 > 0}">
        <a href="controller?command=${commandParam}&page=${page-1}&pageSize=${pageSize}">Previous</a>
    </c:when>
    <c:otherwise>
        Previous
    </c:otherwise>
</c:choose>

        <c:forEach var="p" begin="${minPossible}" end="${maxPagePossible}">
    <c:choose>
        <c:when test="${page == p}">${p}</c:when>
        <c:otherwise>
            <a href="controller?command=${commandParam}&page=${p}&pageSize=${pageSize}">${p}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>

<c:choose>
    <c:when test="${page + 1 <= pageCount}">
        <a href="controller?command=${commandParam}&page=${page+1}&pageSize=${pageSize}">Next</a>
    </c:when>
    <c:otherwise>
        Next
    </c:otherwise>
</c:choose>
    </c:when>
    <c:otherwise>

        <c:choose>
            <c:when test="${page - 1 > 0}">
                <a href="controller?command=${commandParam}&topic=${topic}&page=${page-1}&pageSize=${pageSize}">Previous</a>
            </c:when>
            <c:otherwise>
                Previous
            </c:otherwise>
        </c:choose>

        <c:forEach var="p" begin="${minPossible}" end="${maxPagePossible}">
            <c:choose>
                <c:when test="${page == p}">${p}</c:when>
                <c:otherwise>
                    <a href="controller?command=${commandParam}&topic=${topic}&page=${p}&pageSize=${pageSize}">${p}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${page + 1 <= pageCount}">
                <a href="controller?command=${commandParam}&page=${page+1}&topic=${topic}&pageSize=${pageSize}">Next</a>
            </c:when>
            <c:otherwise>
                Next
            </c:otherwise>
        </c:choose>
    </c:otherwise>
    
</c:choose>

|

<form action="controller" style='display:inline;'>
    <input name="command" value="${commandParam}" type="hidden"/>
    <select name="page">
        <c:forEach begin="1" end="${pageCount}" var="p">
            <option value="${p}" ${p == param.page ? 'selected' : ''}>${p}</option>
        </c:forEach>
    </select>
    <input name="pageSize" value="${pageSize}" type="hidden"/>
    <input type="submit" value="Go"/>
</form>

<div class="container-fluid mt-5">
    <div class="row">
    <c:forEach var="topics" items="${topics}" >
        <div class="col-sm-4">
                <h3>${topics.nameTopic}</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
                <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
            <br>
            <br>
                </div>
    </c:forEach>

<c:forEach var="courses" items="${courses}" >
    <tr>
        <div class="card">
            <h5 class="card-header">${courses.courseName}</h5>
            <div class="card-body">
                <h5 class="card-title">Special title treatment</h5>
                <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                <a href="#" class="btn btn-primary">About course</a>
<%--                <a href="controller?command=joinToCourse&idCourse=${courses.idCourse}" class="btn btn-primary">Join the course</a>--%>
            </div>
        </div>
    </tr>
</c:forEach>



<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
