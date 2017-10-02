package main.control;

import main.JDBC.PostMessage;
import main.JDBC.SendNotification;
import main.JDBC.UserInfro;
import main.JDBC.likeMessage;
import main.model.notificationBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LikePostServlet")
public class LikePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取 自身 userid
        HttpSession session =request.getSession();
        int likeUser_id = (int) session.getAttribute("userid");

        int p_id = Integer.parseInt(request.getParameter("p_id"));
        likeMessage.likeMessage(p_id,likeUser_id);
        //给 该 post的用户发 站内信
        int receiverID = PostMessage.getUseridByP_id(p_id);
        String userName = UserInfro.getUserNameByUserid(likeUser_id);
        if(likeUser_id != receiverID){
            SendNotification.sendnotification(likeUser_id,receiverID,userName+" like your post");
        }

        response.getWriter().write("like success");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
