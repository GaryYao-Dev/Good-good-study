<%--
  Created by IntelliJ IDEA.
  User: kevifunaumac
  Date: 2017/9/26
  Time: 下午10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>header</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <script type="text/javascript">
        function forwardtowall() {
            window.location.href ="postmessage"
        }
        function forwardtohome() {
            window.location.href ="userhomepage.jsp"
        }



    </script>
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">UNSWbook</a>
        </div>
        <div>
            <form class="navbar-form navbar-left" role="search" action="/SearchFriendsServlet" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search" name="search_user_name">
                </div>
                <input type="submit" class="btn btn-default" value="Search friends">
            </form>


            <ul class="nav navbar-nav navbar-right">
                <button type="button" onclick="forwardtowall()" class="btn btn-default navbar-btn">
                    Find Friends
                </button>
                <button type="button" onclick="forwardtowall()" class="btn btn-default navbar-btn">
                    Wall
                </button>
                <button type="button" onclick="forwardtohome()" class="btn btn-default navbar-btn">
                    ${pageContext.session.getAttribute('user_name')}
                </button>
                <button type="button" onclick="forwardtohome()" class="btn btn-default navbar-btn">
                    notification
                </button>

            </ul>
        </div>
    </div>
</nav>


</body>
</html>


