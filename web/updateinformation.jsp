<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<title>update information</title>
</head>
<body>
<div class="container" id="wrap">
<logo>UNSWBOOk</logo>
</br>

<jsp:useBean  id="ad" class="comp9321.add" />

<% 	
	String ss="";
	ss="";
	String username=request.getParameter("username");
	String password1=request.getParameter("originalpassword");
	String password2=request.getParameter("newpassword");
	String name1=request.getParameter("name");
	String name2=request.getParameter("lastname");
	String name=name1+name2;
	String gender=request.getParameter("gender");
	String email=request.getParameter("email");
	String s="";

	String year=(request.getParameter("year"));
	
	String month=(request.getParameter("month"));
	String day=(request.getParameter("day"));


	s=ad.update(username,password1,password2,name,year,month,day,gender,email);
	
	out.print(s);
	if (s=="has updated"){
	    int userid=ad.getid(username);
		String filepath="headphoto"+String.valueOf(userid)+".jpeg";
		ad.newphoto(username,filepath);
	%>
	<br>
	<a href="Login.jsp">Now go to login in our unswbook</a>
	<form method="post" action="changephoto.jsp?userid=<%=userid%>"enctype="multipart/form-data">

	you can update your photo：<input type="file" name="image">
	<br>
	<input type="submit" value="submit" name="submit" size="25">

	<br>
	</form>

	<%
	}
	
%>

</div>
</body>
</html>
