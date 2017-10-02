
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.w3c.dom.*"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean  id="ad" class="main.JDBC.finddetail" />
<%
int useremailname = Integer.valueOf((String) request.getParameter("apply"));
int applyemailname = Integer.valueOf((String) request.getParameter("username"));
out.println("Dear user"+ad.getname(useremailname)+":<br> User  "+ad.getname(applyemailname)+"had be your friend.");
ad.confirm1(useremailname,applyemailname);
%>
</form>
<br>
<a href="Login.jsp">Now go to login in our unswbook</a>
</form>

</body>
</html>
