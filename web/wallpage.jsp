<%@ page import="java.util.HashMap" %>
<%@ page import="main.control.LikePostServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="main.model.postMessageBean" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: kevifunaumac
  Date: 2017/9/23
  Time: 下午12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
  <head>
    <title>wall</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <script type="text/javascript" src="helper.js"></script>


      <style>
          img {
              width: 100%;
              height: auto;
          }
          .single-blog-item {
              border: 1px solid #dfdede;
              box-shadow: 2px 5px 10px #dfdede;
              margin: 15px auto;
              padding: 5px;
              position: relative;
          }
          .blog-content {
              padding: 15px;
          }
          .blog-content h4 {
              font-size: 16px;
              font-weight: 500;
              margin-bottom: 10px;
              text-transform: uppercase;
          }
          .blog-content h4 a{
              color:#777;
          }
          .blog-content p{
              color: #999;
              font-size: 14px;
              font-weight: 300;
              line-height: 1.3333;
          }
          .blog-date{
          }
          .blog-date {
              position: absolute;
              background: #337ab7;
              top: 35px;
              left: 5px;
              color: #fff;
              border-radius: 0 25px 25px 0;
              padding: 5px 15px;
              font-weight: 700;
          }
          .more-btn {
              background: #337ab7;
              border-radius: 2px;
              display: block;
              height: 30px;
              line-height: 30px;
              margin: 30px auto auto auto;
              text-align: center;
              width: 110px;
              color: #f1f1f1;
          }

          .fileinput-button {
              position: relative;
              display: inline-block;
              overflow: hidden;
          }

          .fileinput-button input{
              position:absolute;
              right: 0px;
              top: 0px;
              opacity: 0;
              -ms-filter: 'alpha(opacity=0)';
              font-size: 200px;
          }
          logo{
              color:#2f74bc;
              font-size:35px;
              font-weight:bold;
          }


      </style>


      <script type="text/javascript">
          function contentcheck() {
              var c = document.getElementById("p_content").value;
              if(c == ""){
                  alert("This post appears to be blank. Please write something or attach a link or photo to post.")
              }else {
                  document.getElementById("postMessageForm").submit();
                  alert("waiting.....")
                  setInterval(2000);
                  var j = document.getElementById("jump");
                  j.submit();
              }
          }
      </script>




  </head>


  <body>
  <%@include file="header.jsp"%>



  <div class="col-md-3  text-center"><logo>UNSW Wall</logo></div>

  <%--compose post 上传文字 跟 图片--%>

  <div class="container col-md-3 text-center">
      <button type="button" class="btn btn-primary" data-toggle="collapse"
              data-target="#demo">
          Wanna to post?
      </button>
  </div>

  <div id="demo" class="collapse">
      <div class="container col-md-6 text-center">
          <form id="postMessageForm" action="uploadnewpost" method="post"  enctype="multipart/form-data" target="targetIfr">
              <textarea rows="3" cols="75" id="p_content" name="p_content" placeholder="what's on your mind?"></textarea><br>
              <br />
              <div  class="col-md-3 col-md-offset-2">
        <span class="btn btn-success fileinput-button">
            <span>Upload Picture</span>
            <input type="file" name="p_image">
        </span>
              </div>
              <div class="col-md-3 col-md-offset-2">
                  <button type="button" class="btn btn-success fileinput-button" onclick="contentcheck()">Post</button>
              </div>
          </form>
          <iframe name="targetIfr" style="display:none"></iframe>
      </div>
  </div>



  <%--wall--%>
  <div class="container">
      <div class="row">
    <c:forEach var="postMessage" items="${sessionScope.postMessageList}" >
        <%--自己的post 可删除 可隐藏 --%>
        <c:if test="${postMessage.p_userid == pageContext.session.getAttribute('userid')}">
            <div class="col-md-4" id ="${postMessage.p_id}">
                <button type="button" onclick="toggle('${postMessage.p_id}')">hidden</button>
                <button type="button" onclick="deletepost('${postMessage.p_id}')">delete</button>
                <div class="single-blog-item">
                    <div class="blog-thumnail">
                        <c:if test="${postMessage.p_image !=''}">
                            <a href=""><img src="<%=request.getContextPath()%>uploadImage/${postMessage.p_image}" alt="blog-img" ></a>
                        </c:if>
                    </div>
                    <div class="blog-content">
                        <p class="list-group-item-text">${postMessage.username}</p>
                        <p class="list-group-item-heading">${postMessage.p_content}</p>
                        <button type="button" id ="${postMessage.p_id}+${postMessage.p_userid}" onclick="likeornot(${postMessage.p_id},this.innerHTML,this.id)" >${postMessage.like}</button>
                    </div>
                    <span >${postMessage.p_time}</span>
                    <p>${postMessage.listUseramount}</p>
                    <c:forEach var="likeuser" items="${postMessage.likeUserList}">
                        <p> ${likeuser}</p>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <%--朋友的post 只能隐藏--%>
        <c:if test="${postMessage.p_userid != pageContext.session.getAttribute('userid')}">
            <div class="col-md-4" id ="${postMessage.p_id}">
                <button type="button" onclick="toggle('${postMessage.p_id}')">hidden</button>
                <div class="single-blog-item">
                    <div class="blog-thumnail">
                        <c:if test="${postMessage.p_image != ''}">
                            <a href=""><img src="<%=request.getContextPath()%>uploadImage/${postMessage.p_image}" alt="blog-img" ></a>
                        </c:if>
                    </div>
                    <div class="blog-content">
                        <p class="list-group-item-text">${postMessage.username}</p>
                        <p class="list-group-item-heading">${postMessage.p_content}</p>
                        <button type="button" id ="${postMessage.p_id}+${postMessage.p_userid}" onclick="likeornot(${postMessage.p_id},this.innerHTML,this.id)" >${postMessage.like}</button>
                    </div>
                    <span >${postMessage.p_time}</span>
                    <p>${postMessage.listUseramount}</p>
                    <c:forEach var="likeuser" items="${postMessage.likeUserList}">
                        <p> ${likeuser}</p>
                    </c:forEach>
                </div>
            </div>
         </c:if>

    </c:forEach>


      </div>
  </div>

  <form action="postmessage" method="post" id = "jump"></form>

  </body>
</html>
