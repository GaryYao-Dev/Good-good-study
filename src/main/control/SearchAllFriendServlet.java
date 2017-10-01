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
//
//import  main.JDBC.FriendsSearch;
//import  main.JDBC.FriendsSearch;
//
//@WebServlet("/SearchAllFriendServlet")
//public class SearchAllFriendServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////search all friend
//        List<String> allfriend_list = new ArrayList();
//        HttpSession session = (HttpSession) request.getSession();
//        int userid = (int) session.getAttribute("userid");
//        String getall = (String) session.getAttribute("getall");
//        if (getall != null && getall == "right"){
//            FriendsSearch fs = new FriendsSearch();
//            allfriend_list = fs.getAllFriend(userid);
//        }
//        session.setAttribute("allfriend_list",allfriend_list);
//        response.sendRedirect("/allfriendsearchshow.jsp");
//
////        //search specific firend
////        String user_name = request.getParameter("user_name");
////        if (user_name != null){
////            specificfriend_list = getSpecificFriend(user_name, userid);
////        }
////
////
////        //advanced search
////        String userName = request.getParameter("userName");
////        store_list[0] = userName;
////        String gender = request.getParameter("gender");
////        store_list[1] = gender;
//          String email = request.getParameter("email");
////        store_list[2] = gender;
////        String u_year = request.getParameter("u_year");
////        store_list[2] = u_year;
////        String u_month = request.getParameter("u_month");
////        store_list[3] = u_month;
////        String u_day = request.getParameter("u_day");
////        store_list[4] = u_day;
////        advancedsearch_list =getAdvancedSearch(store_list);
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
