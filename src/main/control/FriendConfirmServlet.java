package main.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.JDBC.friendconfirm.confirmfriend;

@WebServlet("/FriendConfirmServlet")
public class FriendConfirmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String con_email_add = (String) request.getAttribute("con_email_button");
        int  useremailid = (int) request.getAttribute("useremailid");
        int applyemailid = (int) request.getAttribute("applyemailid");
        if (con_email_add == "right"){
            confirmfriend(useremailid, applyemailid);
            response.sendRedirect("/confirm_sucess.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
