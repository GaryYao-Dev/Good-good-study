package main.control;

import main.JDBC.UserInfro;
import main.model.FriendProfileBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "homepageFriendsServlet")
public class homepageFriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取 自身的 userid
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("userid");
        List<FriendProfileBean> f = UserInfro.getFriendlistByuserid(user_id);
        session.setAttribute("homepage_friendlist",f);
        request.getRequestDispatcher("/userhomepage_friends.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
