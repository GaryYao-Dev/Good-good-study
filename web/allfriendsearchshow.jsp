<%--
  Created by IntelliJ IDEA.
  User: jiewang
  Date: 30/9/17
  Time: 1:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    List<String> searchalllist = new ArrayList();
    searchalllist = (List<String>) request.getSession().getAttribute("allfriend_list");
    out.println(searchalllist);


</body>
</html>
