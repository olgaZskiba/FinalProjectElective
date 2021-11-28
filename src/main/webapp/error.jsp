<%--
  Created by IntelliJ IDEA.
  User: olgaz
  Date: 14.11.21
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Message</title>
</head>
<body>
<h2>ERROR!</h2>

<p style="color: red;">${requestScope.message}</p>

${ex.message}
</body>
</html>
