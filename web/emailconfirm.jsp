<%--
  Created by IntelliJ IDEA.
  User: jiewang
  Date: 2/10/17
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String useremailname =(String)request.getParameter("username");
    String applyemailname =(String)request.getParameter("applyname");
    request.getSession().setAttribute("useremailname", useremailname);
    request.getSession().setAttribute("applyemailname", applyemailname);
    String con_email_button = "right";
    request.getSession().setAttribute("con_email_button",con_email_button);
    out.println("Dear user "+useremailname+":\n User "+applyemailname+"want to add you as friend.");
%>

<input type="button" name="button" value="confirm" onclick="javascript:location.href='friendConfirm'"/>

</body>
</html>
