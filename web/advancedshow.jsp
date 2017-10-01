<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jiewang
  Date: 1/10/17
  Time: 12:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>Title</title>
</head>
<body>
<form method="post" action="/SendEmailServlet">
    <table>
<%
    List<String> advancedsearch_list = new ArrayList();
    advancedsearch_list = (List<String>) request.getSession().getAttribute("advancedsearch_list");
    out.println(advancedsearch_list);
    String advanced_email =null;
    String advanced_name = null;
    request.getSession().setAttribute("advanced_email",advanced_email);
    request.getSession().setAttribute("advanced_name",advanced_name);
%>

        <tr>
            <tb><input type="submit" name = "add" value="add"/></tb>
        </tr>
    </table>
</form>

</body>
</html>
