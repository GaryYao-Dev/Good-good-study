<%--
  Created by IntelliJ IDEA.
  User: Gary Yao
  Date: 2017/9/16
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<form action="admin_login_servlet" method="post">
    <table>
        <tr>
            <td>username:</td>
            <td>
                <input name="admin_username">
            </td>
        </tr>
        <tr>
            <td>password:</td>
            <td>
                <input name="admin_password">
            </td>
        </tr>
    </table>
    <input type="submit" value="Login">
</form>

<%
    try {
        if (!(boolean) request.getAttribute("status")) {
            %>
            <script>
                window.alert("Login Failed, please try again.")
            </script>
<%
        }
    }catch (Exception e){

    }
%>
</body>
</html>
