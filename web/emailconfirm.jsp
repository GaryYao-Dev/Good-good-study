<%--
  Created by IntelliJ IDEA.
  User: jiewang
  Date: 2/10/17
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/FriendConfirmServlet" method="get">
<%
String useremailname = (String) request.getAttribute("useremailname");
String applyemailname = (String) request.getAttribute("applyemailname");
out.println("Dear user"+useremailname+":\n User"+applyemailname+"want to add you as friend.");
String con_email_button = "right";
request.getSession().setAttribute("con_email_button",con_email_button);
%>
    <input type="submit" value="confirm">
</form>

</body>
</html>
