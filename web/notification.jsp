
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="main.control.LikePostServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="main.model.postMessageBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script>
        function dosomething(a){
            window.location.href="notification.jsp?deletenum="+a;
            alert("has read this message!"+a)
        }
    </script>


    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title>notification</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container" id="wrap">
    </br>
    <jsp:useBean  id="ad" class="comp9321.notific" />
    <%

        
        int userid=(Integer) (request.getSession().getAttribute("userid"));
        String delete=request.getParameter("deletenum");
        if (delete!=null){
            ad.delete(userid,delete);
        }

        List str=ad.getstr(userid);
        request.setAttribute("uList",str);

    %>
<c:forEach items="${uList}" var="info">
    ${info}
    <button id="item" onclick="dosomething('${uList.indexOf(info)}')">read</button>
    <br>
</c:forEach>

</div>
</body>
</html>
