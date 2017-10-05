package main.control;

import main.JDBC.FriendsSearch;
import main.admin.admin_model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static main.JDBC.Friendemail.SendUNSWemail;
import static main.JDBC.UserInfro.getUserEmailByUserid;
import static main.JDBC.UserInfro.getUserNameByUserid;
import static main.JDBC.UserInfro.getUser_nameByUserid;

@WebServlet(name = "AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取 自身的 userid
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("userid");
        System.out.print("______");
        //在数据库中 插入新记录
        int friend_id = Integer.parseInt(request.getParameter("friend_id"));

        FriendsSearch.addFriend(user_id,friend_id);
        FriendsSearch.addFriend(friend_id,user_id);

        admin_model am  = new admin_model();
        try {
            am.log_add_friend(user_id, friend_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //发送 邮件
        String userName = getUser_nameByUserid(user_id);
        String firend_name = getUser_nameByUserid(friend_id);
        String email = getUserEmailByUserid(friend_id);
        SendUNSWemail(userName, firend_name,email);

        response.getWriter().write("add friend success");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
