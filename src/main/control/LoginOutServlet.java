package main.control;

import main.admin.admin_model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        admin_model ad = new admin_model();
        try {
            ad.log_out((Integer) session.getAttribute("userid"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.removeAttribute("userid");
        //log

        session.invalidate();
        response.sendRedirect("/login.jsp");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doPost(request,response);
    }
}
