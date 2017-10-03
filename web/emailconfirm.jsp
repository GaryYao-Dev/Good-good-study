<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<div class="container" id="wrap">
    <logo>UNSWBOOk</logo>
    </br>

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
    </br>
</div>
</body>
</html>
