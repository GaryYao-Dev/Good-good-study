<%--
  Created by IntelliJ IDEA.
  User: kevifunaumac
  Date: 2017/9/26
  Time: 下午10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    function log()
    {
        var xmlhttp = new XMLHttpRequest();
        var userID = "${userid}";
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                document.getElementById("log").innerHTML=xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET","/admin?action=log&&userID="+userID,true);
        xmlhttp.send();
    }
</script>

<html>
<head>
    <title>homepage</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



    <style>
        .fb-profile-block {
            margin: auto;
            position: relative;
            width: 850px;
        }
        img{
            width: 100%;
            height: auto;
        }
        .fb-profile-block-thumb{
            display: block;
            height: 315px;
            overflow: hidden;
            position: relative;
            text-decoration: none;
        }
        .fb-profile-block-menu {
            border: 1px solid #d3d6db;
            border-radius: 0 0 3px 3px;
        }
        .profile-img a{
            bottom: 15px;
            box-shadow: none;
            display: block;
            left: 15px;
            padding:1px;
            position: absolute;
            height: 160px;
            width: 160px;
            background: rgba(0, 0, 0, 0.3) none repeat scroll 0 0;
            z-index:9;
        }
        .profile-img img {
            background-color: #fff;
            border-radius: 2px;
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.07);
            height:158px;
            padding: 5px;
            width:158px;
        }
        .profile-name {
            bottom: 60px;
            left: 205px;
            position: absolute;
        }
        .profile-name h2 {
            color: #fff;
            font-size: 24px;
            font-weight: 600;
            line-height: 30px;
            max-width: 275px;
            position: relative;
            text-transform: uppercase;
        }
        .fb-profile-block-menu{
            height: 44px;
            position: relative;
            width:850px;
            overflow:hidden;
        }
        .block-menu {
            clear: right;
            padding-left: 205px;
        }
        .block-menu ul{
            margin:0;
            padding:0;
        }
        .block-menu ul li{
            display:inline-block;
        }
        .block-menu ul li a {
            border-right: 1px solid #e9eaed;
            float: left;
            font-size: 14px;
            font-weight: bold;
            height: 42px;
            line-height: 3.0;
            padding: 0 17px;
            position: relative;
            vertical-align: middle;
            white-space: nowrap;
            color:#4b4f56;
            text-transform:capitalize;
        }
        .block-menu ul li:first-child a{
            border-left: 1px solid #e9eaed;
        }


    </style>
</head>
<body>
<%@include file="header.jsp"%>

<div class="fb-profile-block">
    <div class="fb-profile-block-thumb"><img src="http://themeboxer.com/demo/snipp/coverpage.jpg" alt="" title=""></div>
    <div class="profile-img"><a href="#"><img src="http://themeboxer.com/demo/snipp/profile.jpg" alt="" title=""></a></div>
    <div class="profile-name">
        <h2>Profile Name</h2>
    </div>

    <div class="fb-profile-block-menu">
        <div class="block-menu">
            <ul>
                <li><a href="#" onclick="log()">Timeline</a></li>
                <li><a href="#">about</a></li>
                <li><a href="#">Friends</a></li>
                <li><a href="#">Photos</a></li>
                <li><a href="#">More...</a></li>
            </ul>
        </div>
    </div>

</div>

<div id="log">

</div>




</body>
</html>
