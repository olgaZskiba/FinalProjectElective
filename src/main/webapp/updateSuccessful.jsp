<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 19.11.21
  Time: 01:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>

    <title>UpdateSuccessful</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<%--<form method="POST" action="controller">--%>
<%--    <input type="hidden" name="command" value="studentMainMenu"/>--%>

    <h3>${sessionScope.login} was add to course ${sessionScope.idCourse}</h3>

<%--    <td><input type="submit" value="Return to main menu"></td>--%>

<%--</form>--%>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
