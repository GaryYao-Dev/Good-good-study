package main.control;

import main.JDBC.FriendsSearch;
import main.JDBC.UserInfro;
import main.model.EdgeBean;
import main.model.FriendProfileBean;
import main.model.postMessageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FindFriendsServlet")
public class FindFriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取 自身的 userid
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("userid");

        List<FriendProfileBean> findfriend_list = new ArrayList<>();
        List<FriendProfileBean> unconfirm_list = new ArrayList<>();

        // for Graph visualization
        List<FriendProfileBean> Guserlist = new ArrayList<>();
        List<postMessageBean> Gpostlist = new ArrayList<>();
        List<EdgeBean> Gfriendship = new ArrayList<>();
        List<EdgeBean> Gpostship = new ArrayList<>();
        List<EdgeBean> Glikeship = new ArrayList<>();

        Guserlist = UserInfro.getUserlist();
        Gpostlist = UserInfro.getpostlist();
        Gfriendship = UserInfro.getfriendship();
        Gpostship = UserInfro.getpostship();
        Glikeship = UserInfro.getlikeship();

        //unconfirm friends list
        unconfirm_list = FriendsSearch.getUnConfirmFriendByUserid(user_id);

        // advance search
        //接受参数
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String u_year = request.getParameter("u_year");
        String u_month = request.getParameter("u_month");
        String u_day = request.getParameter("u_day");
        String _list[] ={userName,gender,u_year,u_month,u_day};
        findfriend_list = FriendsSearch.getAdvancedSearch(_list,user_id);


        session.setAttribute("findfriend_list",findfriend_list);
        session.setAttribute("unconfirm_list",unconfirm_list);


        session.setAttribute("Guserlist",Guserlist);
        session.setAttribute("Gpostlist",Gpostlist);
        session.setAttribute("Gfriendship",Gfriendship);
        session.setAttribute("Gpostship",Gpostship);
        session.setAttribute("Glikeship",Glikeship);


        response.sendRedirect("/findfriends.jsp");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
