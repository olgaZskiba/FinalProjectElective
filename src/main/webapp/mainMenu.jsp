<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 21.11.21
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"--%>
<%--          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"--%>
<%--            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"--%>
<%--            crossorigin="anonymous"></script>--%>

    <title>Courses Info</title>
    <%--    <style>--%>
    <%--        td {--%>
    <%--            border-bottom: 1px solid #ddd;--%>
    <%--            padding: 5px;--%>
    <%--        }--%>
    <%--    </style>--%>
</head>

<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<%--<div style="padding: 5px;">--%>

<%--    <a href="${pageContext.request.contextPath}/">Home</a>--%>
<%--    |--%>
<%--    <a href="${pageContext.request.contextPath}/login.jsp">Login</a>--%>
<%--    |--%>
<%--    &lt;%&ndash;    <a href="${pageContext.request.contextPath}/topicList">Topic List</a>&ndash;%&gt;--%>
<%--    Sort courses by:--%>
<%--    <a href="${pageContext.request.contextPath}/controller?command=sortCoursesByAtoZ&page=1&pageSize=4">Sort courses A->Z</a>--%>
<%--    |--%>
<%--    <a href="${pageContext.request.contextPath}/controller?command=sortCoursesByZtoA&page=1&pageSize=4">Sort courses Z->A</a>--%>
<%--    |--%>
<%--    <a href="${pageContext.request.contextPath}/controller?command=sortCoursesByDuration&page=1&pageSize=4">Sort courses by Duration</a>--%>
<%--    |--%>
<%--    <a href="${pageContext.request.contextPath}/controller?command=sortCoursesByStudentsCount&page=1&pageSize=4">Sort courses by Students</a>--%>
<%--    |--%>
<%--    <a href="${pageContext.request.contextPath}/login.jsp">Login</a>--%>

<%--</div>--%>


Page ${page} of ${pageCount}

|

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
<c:forEach var="courseList" items="${courseList}">
    <div class="col-sm-4">
        <h3>${courseList.courseName}</h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
        <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
        <a href="#" class="btn btn-primary">About course</a>
        <a href="controller?command=joinToCourse&idCourse=${courseList.idCourse}" class="btn btn-primary">Join the course</a>
        <br>
        <br>
    </div>
<%--    <tr>--%>
<%--        <div class="card">--%>
<%--            <h5 class="card-header">${courseList.courseName}</h5>--%>
<%--            <div class="card-body">--%>
<%--                <h5 class="card-title">Special title treatment</h5>--%>
<%--                <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>--%>
<%--                <a href="#" class="btn btn-primary">Go somewhere</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </tr>--%>
</c:forEach>

<c:forEach var="sortedAZList" items="${sortedAZList}">
    <tr>
        <div class="card">
            <h5 class="card-header">${sortedAZList.courseName}</h5>
            <div class="card-body">
                <h5 class="card-title">Special title treatment</h5>
                <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                <a href="#" class="btn btn-primary">About course</a>
<%--                <a href="controller?command=joinToCourse&idCourse=${sortedAZList.idCourse}" class="btn btn-primary">Join the course</a>--%>
            </div>
        </div>
    </tr>
</c:forEach>

<c:forEach var="sortedZAList" items="${sortedZAList}">
    <tr>
        <div class="card">
            <h5 class="card-header">${sortedZAList.courseName}</h5>
            <div class="card-body">
                <h5 class="card-title">Special title treatment</h5>
                <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                <a href="#" class="btn btn-primary">About course</a>
<%--                <a href="controller?command=joinToCourse&idCourse=${sortedZAList.idCourse}" class="btn btn-primary">Join the course</a>--%>
            </div>
        </div>
    </tr>
</c:forEach>

<c:forEach var="sortedDurationList" items="${sortedDurationList}">
    <tr>
        <div class="card">
            <h5 class="card-header">${sortedDurationList.courseName}</h5>
            <div class="card-body">
                <h5 class="card-title">Special title treatment</h5>
                <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                <a href="#" class="btn btn-primary">About course</a>
<%--                <a href="controller?command=joinToCourse&idCourse=${sortedDurationList.idCourse}" class="btn btn-primary">Join the course</a>--%>
            </div>
        </div>
    </tr>
</c:forEach>

<c:forEach var="sortedByStudentsCountList" items="${sortedByStudentsCountList}">
    <tr>
        <div class="card">
            <h5 class="card-header">${sortedByStudentsCountList.courseName}</h5>
            <div class="card-body">
                <h5 class="card-title">Special title treatment</h5>
                <p class="card-text">${sortedByStudentsCountList.studentCount} students are accepted for this course</p>
                <a href="#" class="btn btn-primary">About course</a>
<%--                <a href="controller?command=joinToCourse&idCourse=${sortedByStudentsCountList.idCourse}" class="btn btn-primary">Join the course</a>--%>
            </div>
        </div>
    </tr>
</c:forEach>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>

