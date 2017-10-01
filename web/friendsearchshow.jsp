<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: jiewang
  Date: 30/9/17
  Time: 1:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%//form
    List<String> searchspecificlist = new ArrayList();
    searchspecificlist = (List<String>) request.getSession().getAttribute("specificfriend_list");
    if (searchspecificlist.size() != 0) {
        out.print(searchspecificlist);
        String con_add = "right";
        request.getSession().setAttribute("con_add", con_add);
        String specific_name =null;
        String specific_email =null;
        request.getSession().setAttribute("specific_name", specific_name);
        request.getSession().setAttribute("specific_email", specific_email);
    }
%>

<form method="post" action="/SendEmailServlet">
    <table>
        <tr>
            <tb><input type="submit" name = "add" value="add"/></tb>
        </tr>
    </table>
</form>

</body>
</html>
