package main.control;

import main.JDBC.FriendsSearch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import main.JDBC.FriendsSearch;

@WebServlet("/SearchFriendsServlet")
public class SearchFriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> specificfriend_list = new ArrayList();
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");

        String user_name = request.getParameter("search_user_name");

        if (user_name != null){
            specificfriend_list = FriendsSearch.getSpecificFriend(user_name, userid);
        }
        session.setAttribute("specificfriend_list",specificfriend_list);
        response.sendRedirect("/friendsearchshow.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
