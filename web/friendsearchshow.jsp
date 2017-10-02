<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: jiewang
  Date: 30/9/17
  Time: 1:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="helper.js"></script>

    <style type="text/css">
        .top {
            text-align: center;
        }

        .shadow {
            box-shadow: 0px 0px 5px 0px;
            padding-top: 10px;
        }

        h2, a{
            color: #fff;
        }
    </style>

</head>
<body>
<%@ include file="header.jsp"%>


<div class="container">
    <div class="top">
        <h2>Friend's List</h2>
    </div>
    <div class="row">
        <div class="shadow">
            <c:forEach var="u" items="${specificfriend_list}">

                <c:if test="${u.userid != sessionScope.userid}">
                <div class="col-sm-12">
                    <div class="col-sm-2">
                        <img class="img-circle" width="60px">
                    </div>

                    <div class="col-sm-8">
                        <h4>${u.userName}</h4>
                        <p>${u.gender}</p>
                        <p>${u.u_year}-${u.u_month}-${u.u_day}</p>
                        <p>${u.email}</p>
                    </div>
                    <div class="col-sm-2">
                        <br>
                        <button type="button" id ="${u.userid}" onclick="addfriend(${u.userid})">Add friend </button>
                    </div>
                </div>
                <div class="clearfix"></div>
                <hr />
                </c:if>
            </c:forEach>

        </div>
    </div>






<script type="text/javascript">
    function addfriend(friend_id) {
        var req = getXMLHttpRequest();
        req.onreadystatechange = function ()
        {

            if(req.readyState == 4)
            {
                if(req.status == 200)
                {
                    var b = document.getElementById(friend_id);
                    b.innerHTML = "request sent";
                    alert(req.responseText);
                    b.disabled = true;

                }
            }
        }
        req.open("get", "addfriend?friend_id=" + friend_id);
        req.send(null);
    }
</script>







</body>
</html>
