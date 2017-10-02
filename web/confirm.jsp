<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<title>Confirm</title>
</head>
<body>
<div class="container" id="wrap">
<logo>UNSWBOOk</logo>
</br>
<jsp:useBean  id="ad" class="comp9321.add" />
<%
String username=request.getParameter("username");
out.println(ad.confirm(username));
String file11=request.getRealPath("/");

String filepath="headphoto"+String.valueOf(ad.number())+".jpeg";
ad.newphoto(username,filepath);
%>

<form method="post" action="photo.jsp"enctype="multipart/form-data"> 

choose your photo：<input type="file" name="image"> 
<br> 
<input type="submit" value="submit" name="submit" size="25"> 

<br> 
</form> 
</div>
</body>
</html>
