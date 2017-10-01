<%--
  Created by IntelliJ IDEA.
  User: jiewang
  Date: 30/9/17
  Time: 11:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
String user_name =(String)request.getParameter("applyname");
String friendname =(String)request.getParameter("username");
out.println("Dear user"+friendname+":\n User"+user_name+"want to add you as friend.");
session.setAttribute("user_name", user_name);
session.setAttribute("friendname", friendname);
%>
<form action="/FriendConfirmServlet" method="get">
        <input type="submit" value="confirm">
</form>

</body>
</html>
