<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gary
  Date: 2017/9/30
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h5>LOG</h5>
<table>
    <c:forEach items="${log}" var="row">
        <tr><td>Activity</td><td>${row.get(0)}</td></tr>
        <tr bgcolor="#fff8dc"><td>Time</td><td>${row.get(1)}</td></tr>
    </c:forEach>
</table>
</body>
</html>
