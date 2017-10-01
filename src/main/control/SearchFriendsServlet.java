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

@WebServlet("SearchFriendsServlet")
public class SearchFriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> specificfriend_list = new ArrayList();
        HttpSession session = (HttpSession) request.getSession();
        int userid = (int) session.getAttribute("userid");

        //search specific firend`
        String user_name = request.getParameter("user_name");
        if (user_name != null){
            FriendsSearch fs = new FriendsSearch();
            specificfriend_list = fs.getSpecificFriend(user_name, userid);
        }
        session.setAttribute("specificfriend_list",specificfriend_list);
        response.sendRedirect("/friendsearchshow.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
