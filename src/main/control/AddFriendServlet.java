package main.control;

import main.JDBC.FriendsSearch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取 自身的 userid
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("userid");
        System.out.print("______");
        //在数据库中 插入新记录
        int friend_id = Integer.parseInt(request.getParameter("friend_id"));
        System.out.print(user_id);
        System.out.print(friend_id);
        FriendsSearch.addFriend(user_id,friend_id);

        //发送 邮件


        response.getWriter().write("add friend success");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
