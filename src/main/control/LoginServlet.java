package main.control;

import main.JDBC.DButil;
import main.JDBC.UserInfro;
import main.JDBC.login;
import main.admin.admin_model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import static main.JDBC.login. getlogin;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password = request.getParameter("password");

        Boolean login_confirm=  login.getlogin(username, password);
        System.out.println(login_confirm);
        HttpSession session = request.getSession();


        if (login_confirm){
            int userid = UserInfro.getUseridByUsername(username);
            session.setAttribute("userid",userid);
            System.out.println(userid);
            session.setAttribute("user_name",username);
            String userName = UserInfro.getUserNameByUserid(userid);
            session.setAttribute("userName",userName);
            admin_model am  = new admin_model();
            try {
                am.log_login(userid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("postmessage").forward(request,response);
        }else{
            session.setAttribute("login_confirm",login_confirm);
            response.sendRedirect("/login.jsp");
        }
    }
}

