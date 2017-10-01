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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password = request.getParameter("password");
        String login_confirm=  login.getlogin(username, password);
        if (login_confirm == "right"){
            String user_name = username;
            HttpSession session = request.getSession();
            int userid = UserInfro.getUseridByUsername(username);
            session.setAttribute("userid",userid);
            session.setAttribute("user_name",user_name);
            request.getRequestDispatcher("postmessage").forward(request,response);
        }else{
            HttpSession session=request.getSession();
            session.setAttribute("login_confirm",login_confirm);
            response.sendRedirect("/login.jsp");
        }

    }
}

