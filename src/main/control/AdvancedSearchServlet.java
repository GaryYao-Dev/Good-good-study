//package main.control;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import main.JDBC.FriendsSearch;
//
//
//@WebServlet(name = "AdvancedSearchServlet")
//public class AdvancedSearchServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String [] store_list = new String[5];
//        List<String> advancedsearch_list = new ArrayList();
//        //advanced search
//        String userName = request.getParameter("userName");
//        store_list[0] = userName;
//        String gender = request.getParameter("gender");
//        store_list[1] = gender;
//        String u_year = request.getParameter("u_year");
//        store_list[2] = u_year;
//        String u_month = request.getParameter("u_month");
//        store_list[3] = u_month;
//        String u_day = request.getParameter("u_day");
//        store_list[4] = u_day;
//        FriendsSearch fs = new FriendsSearch();
//        advancedsearch_list =fs.getAdvancedSearch(store_list);
//        HttpSession session = (HttpSession) request.getSession();
//        session.setAttribute("advancedsearch_list",advancedsearch_list);
//        response.sendRedirect("/advancedshow.jsp");
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
