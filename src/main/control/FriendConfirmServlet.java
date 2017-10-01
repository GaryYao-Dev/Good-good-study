package main.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.JDBC.friendconfirm.confirmfriend;

@WebServlet(name = "FriendConfirmServlet")
public class FriendConfirmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = (HttpSession) request.getSession();
        String user_name = (String) session.getAttribute("user_name");
        String friendname = (String) session.getAttribute("friendname");
        if (user_name != null && friendname != null){
            confirmfriend(user_name, friendname);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
