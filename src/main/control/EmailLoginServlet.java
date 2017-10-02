package main.control;

import main.JDBC.UserInfro;
import main.JDBC.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.JDBC.UserInfro.getUseridByUser_name;

@WebServlet("EmailLoginServlet")
public class EmailLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String useremailname = (String) request.getAttribute("useremailname");
        String applyemailname = (String) request.getAttribute("applyemailname");
        if (username == useremailname) {
            Boolean login_confirm = login.getlogin(username, password);
            HttpSession session = request.getSession();
            if (login_confirm) {
                int applyemailid = UserInfro.getUseridByUsername(applyemailname);
                int useremailid = getUseridByUser_name(useremailname);
                session.setAttribute("applyemailid", applyemailid);
                session.setAttribute("useremailid", useremailid);
                response.sendRedirect("/emailconfirm.jsp");
            } else {
                session.setAttribute("login_confirm", login_confirm);
                response.sendRedirect("/login.jsp");
            }

        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
