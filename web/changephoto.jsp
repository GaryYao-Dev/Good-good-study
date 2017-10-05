<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title>Insert title here</title>
</head>
<body>
<div class="container" id="wrap">
    <logo>UNSWBOOk</logo>
    </br>
    Everything is ok!
    <br>
    <a href="login.jsp">Now go to login in our unswbook</a>
    <jsp:useBean  id="ad" class="comp9321.add" />
    <%
        int rubbish=Integer.valueOf(request.getParameter("userid"));
        int len=request.getContentLength();
        byte buffer[]=new byte[len];
        InputStream in=request.getInputStream();
        String ppp=request.getServletContext().getRealPath("/")+"headphoto";
        if (!(new java.io.File(ppp).isDirectory())) {
            new java.io.File(ppp).mkdir();
        }
        java.io.File myFile = new java.io.File(ppp+"/headphototest.txt");
        java.io.FileOutputStream fout = null;
        fout = new java.io.FileOutputStream(myFile);
        byte b[]= "你好！".getBytes();
        fout.write(b);
        fout.flush();  //写入文件
        fout.close();  //关闭
        String path=request.getServletContext().getRealPath("/headphoto/")+"headphototest.txt";
        String filepath=request.getServletContext().getRealPath("/headphoto/")+"headphoto"+String.valueOf(rubbish)+".jpeg";
        RandomAccessFile raf=new RandomAccessFile(path,"r");
        OutputStream out1=new FileOutputStream(filepath);
        OutputStream out2=new FileOutputStream(path);
        int c=0;
        int number=0;
        while ((c=in.read())!=-1){
            out2.write(c);
            out.flush();
            number=number+1;
        }
        this.log(raf.readLine());
        this.log(raf.readLine());
        this.log(raf.readLine());
        this.log(raf.readLine());
        int d=0;
        number=number-4-6;
        while ((d=raf.read())!=-1){
            number=number-1;

            out1.write(d);
            out1.flush();

        }
        in.close();
        out2.close();
        out1.close();
    %>

</div>
</body>
</html>
