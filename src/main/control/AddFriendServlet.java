package main.control;

import main.JDBC.FriendsSearch;
import main.JDBC.finddetail;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;

import comp9321.SendEmail;
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
        String user1=finddetail.getname(user_id);
        String user2=finddetail.getname(friend_id);
        String email=finddetail.getemail(friend_id);
        String name1=finddetail.getuser(user_id);
        String name2=finddetail.getuser(friend_id);
        String content="Dear user "+user2+": \nThis is a e-mail to confirm add friend request on our website from user "+user1+"\nIf you want to have a look of the person,please use this url in your browser.http://localhost:8080/emailconfirm.jsp?apply="+user_id+"&username="+friend_id+"";
        System.out.println(content);
        System.out.println(email);
        try {
            SendEmail.sendmail1(email,content);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }


        response.getWriter().write("add friend success");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
