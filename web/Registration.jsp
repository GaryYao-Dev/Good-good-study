<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="java.io.*"%>
<%@ page import="java.io.IOException"%> 
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<title>Registration</title>
</head>
<body>
<div class="container" id="wrap">
<logo>UNSWBOOk</logo>
</br>

<jsp:useBean  id="ad" class="comp9321.add" />
<jsp:useBean  id="sendemail" class="comp9321.SendEmail" />

<% 	
	String ss="";
	ss="";
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	String name1=request.getParameter("name");
	String name2=request.getParameter("lastname");
	String name=name1+" "+name2;
	String gender=request.getParameter("gender");
	String email=request.getParameter("email");
	String s="";

	try {
	int year=Integer.valueOf(request.getParameter("year"));
	
	int month=Integer.valueOf(request.getParameter("month"));
	int day=Integer.valueOf(request.getParameter("day"));


	s=ad.test(username,password,name,year,month,day,gender,email);
	%>

	<%=s%>
	<%} catch(Exception e){
		out.print("some detail information must be wrong!");
	}

	
%>
<% 
if (s.equals("A confirm e-mail has been sent to your e-mail")){
	try {
		sendemail.sendmail(email,username);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

%>

</br>
<a href="Registrationhome.jsp">back to Register</a>
</div>
</body>
</html>
