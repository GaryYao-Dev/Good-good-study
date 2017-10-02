package main.admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/admin_login_servlet")
public class admin_login_servlet extends HttpServlet{

    admin_model admin = new admin_model();



    public boolean check_auth(String name, String pwd) throws SQLException {

        ArrayList<ArrayList> admin_list = admin.getTable("select * from admin");
        for (ArrayList row: admin_list){
            if (name.equals(row.get(1)))
                if (pwd.equals(row.get(2)))
                    return true;
                else return false;
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("admin_username");
        String pwd = req.getParameter("admin_password");
        try {
            if (check_auth(name, pwd)){
                req.setAttribute("admin_username", name);
                RequestDispatcher view = req.getRequestDispatcher("admin?action=ShowUsers&&userName=");
                view.forward(req, resp);
            }
            else {
                req.setAttribute("status", false);
                RequestDispatcher view = req.getRequestDispatcher("admin_login.jsp");
                view.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
