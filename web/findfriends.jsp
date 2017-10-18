<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kevifunaumac
  Date: 2017/10/1
  Time: 下午9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="helper.js"></script>
    <script src="http://visjs.org/dist/vis.js"></script>
    <link href="http://visjs.org/dist/vis.css" rel="stylesheet" type="text/css" />
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">

    <style type="text/css">
        #mynetwork {
            width: 500px;
            height: 500px;
            border: 1px solid #444444;
            background-color: rgba(249, 253, 255, 0.97);
        }
    </style>
</head>
<body>

<%@include file="header.jsp"%>
<form action="findfriends" method="get">
    <table  class="table">
        <tr>
            <td>userName</td>
            <td>email</td>
            <td>gender</td>
            <td>u_year</td>
            <td>u_month</td>
            <td>u_day</td>
        </tr>
        <tr>
            <td><input type="text" name="userName"></td>
            <td><input type="text" name="email"></td>
            <td><input type="text" name="gender"></td>
            <td><input type="text" name="u_year"></td>
            <td><input type="text" name="u_month"></td>
            <td><input type="text" name="u_day"></td>
        </tr>
        <input type="submit" value="Advance search">

    </table>
</form>

<CENTER>
    <div id="mynetwork"></div>
</CENTER>




<div class="container">
    <div class="top">
        <h2>Sent friend requests</h2>
    </div>
    <div class="row">
        <div class="shadow">
            <c:forEach var="u" items="${unconfirm_list}">

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
                            <button type="button" id ="${u.userid}" onclick="cancelrequest(${u.userid})"> cancel request</button>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <hr />
                </c:if>
            </c:forEach>

        </div>
    </div>
</div>

<div class="container">
    <div class="top">
        <h2>People you may know</h2>
    </div>
    <div class="row">
        <div class="shadow">
            <c:forEach var="u" items="${findfriend_list}">

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
                        var j = document.getElementById("jump");
                        j.submit();



                    }
                }
            }
            req.open("get", "addfriend?friend_id=" + friend_id);
            req.send(null);
        }


        function cancelrequest(friend_id) {
            var req = getXMLHttpRequest();
            req.onreadystatechange = function ()
            {

                if(req.readyState == 4)
                {
                    if(req.status == 200)
                    {
                        var b = document.getElementById(friend_id);
                        b.innerHTML = "cancel sent";
                        alert(req.responseText);
                        b.disabled = true;
                        var j = document.getElementById("jump");
                        j.submit();
                    }
                }
            }
            req.open("get", "cancelfriendrequest?friend_id=" + friend_id);
            req.send(null);
        }

    </script>
    <form id="jump" action="findfriends"  method="get"></form>



    <script type="text/javascript">

    var nodes = [


        <c:forEach var="u" items="${Guserlist}">

        {id:'${u}', group: 'friends'},

        </c:forEach>

        <c:forEach var="u" items="${Gpostlist}">

        {id:'${u}', group: 'posts'},

        </c:forEach>

    ];
    var edges = [

        <c:forEach var="f" items="${Gfriendship}">
        {from: ${f.from}, to: ${f.to}},
        </c:forEach>

        <c:forEach var="f" items="${Gpostship}">
        {from: ${f.from}, to: ${f.to} ,title: 'post',color:{color:"#1bff18"}},
        </c:forEach>

        <c:forEach var="f" items="${Glikeship}">
        {from: ${f.from}, to: ${f.to}, title: 'like',color:{color:"#ff4056"}},
        </c:forEach>

    ]

    // create a network
    var container = document.getElementById('mynetwork');
    var data = {
        nodes: nodes,
        edges: edges
    };
    var options = {
        nodes: {
            shape: 'dot',
            size: 20,
            font: {
                size: 15,
                color: '#ffffff'
            },
            borderWidth: 2
        },
        edges: {
            width: 2
        },
        groups: {

            posts: {
                color:'rgb(0,255,140)',
                shape: 'icon',
                icon: {
                    face: 'FontAwesome',
                    code: '\uf044',
                    size: 50,
                    color: 'green'
                }

            },
            friends: {
                shape: 'icon',
                icon: {
                    face: 'FontAwesome',
                    code: '\uf007',
                    size: 50,
                    color: '#2c239c'
                }
            },
            user: {
                color:{border:'white'},
                shape: 'icon',
                icon: {
                    face: 'FontAwesome',
                    code: '\uf113',
                    size: 50,
                    color: '#0a1015'
                }

            }
        }
    };

    var network = new vis.Network(container, data, options);


</script>




</body>
</html>
