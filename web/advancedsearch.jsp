<%--
  Created by IntelliJ IDEA.
  User: jiewang
  Date: 29/9/17
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/AdvancedSearchServlet" method="get">
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
        <input type="submit" value="search">

    </table>

</form>


</body>
</html>
