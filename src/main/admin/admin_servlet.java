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


@WebServlet("/admin")
public class admin_servlet extends HttpServlet{

    admin_model admin = new admin_model();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        System.out.println("Action:"+action);

        if(action.equals("test")){
            Integer userID = Integer.parseInt(req.getParameter("userID"));
            req.setAttribute("userid", userID);
            RequestDispatcher view = req.getRequestDispatcher("userhomepage.jsp");
            view.forward(req, resp);
        }

        if(action.equals("ShowUsers")) {
            String userName = req.getParameter("userName");
            req.setAttribute("username", showUsers(userName));
            req.setAttribute("ban", showBan(userName));
            RequestDispatcher view = req.getRequestDispatcher("admin.jsp");
            view.forward(req, resp);
        }

        if (action.equals("log")){
            Integer userID = Integer.parseInt(req.getParameter("userID"));
            req.setAttribute("log", showLog(userID));
            RequestDispatcher view = req.getRequestDispatcher("log.jsp");
            view.forward(req, resp);
        }

        if (action.equals("log_for_admin")){
            Integer userID = Integer.parseInt(req.getParameter("userID"));
            req.setAttribute("log", showLog(userID));
            RequestDispatcher view = req.getRequestDispatcher("log_for_admin.jsp");
            view.forward(req, resp);
        }

        if (action.equals("ban")){
            Integer userID = Integer.parseInt(req.getParameter("userID"));
            try {
                Ban(userID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("username", showUsers(""));
            req.setAttribute("ban", showBan(""));
            RequestDispatcher view = req.getRequestDispatcher("admin.jsp");
            view.forward(req, resp);
        }

        if (action.equals("remove")){
            Integer userID = Integer.parseInt(req.getParameter("userID"));
            try {
                Remove(userID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("username", showUsers(""));
            req.setAttribute("ban", showBan(""));
            RequestDispatcher view = req.getRequestDispatcher("admin.jsp");
            view.forward(req, resp);
        }

    }

    private ArrayList<ArrayList> showUsers(String userName){
        ArrayList<ArrayList> user_list = null;
        try {
            user_list = admin.getTable("SELECT userid,user_name from users WHERE confirm=1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userName);

        ArrayList<ArrayList> result = new ArrayList<>();
        if (userName.equals("")) {
            for (ArrayList row:user_list) {
                ArrayList temp = new ArrayList<>();
                temp.add(row.get(0));
                temp.add(row.get(1));
                result.add(temp);
            }

        } else {
            for (ArrayList row:user_list) {
                if (((String) row.get(1)).contains(userName)){
                    ArrayList temp = new ArrayList<>();
                    temp.add(row.get(0));
                    temp.add(row.get(1));
                    result.add(temp);
                }
            }
        }
        return result;
    }

    private ArrayList<ArrayList> showLog(Integer userID){
        ArrayList<ArrayList> log_list = null;
        try {
            log_list = admin.getTable(
                    "SELECT activity, time From log WHERE userID="+userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userID);
        ArrayList<ArrayList> result = new ArrayList<>();
        for (ArrayList row:log_list) {
            ArrayList temp = new ArrayList<>();
            temp.add(row.get(0));
            temp.add(row.get(1));
            result.add(temp);
        }
        return result;
    }
    private ArrayList<ArrayList> showBan(String userName){
        ArrayList<ArrayList> ban_list = null;
        try {
            ban_list = admin.getTable("SELECT userid,user_name from users WHERE confirm=0");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList> result = new ArrayList<>();
        if (userName.equals("")) {
            for (ArrayList row:ban_list) {
                ArrayList temp = new ArrayList<>();
                temp.add(row.get(0));
                temp.add(row.get(1));
                result.add(temp);
            }

        } else {
            for (ArrayList row:ban_list) {
                if (((String) row.get(1)).contains(userName)){
                    ArrayList temp = new ArrayList<>();
                    temp.add(row.get(0));
                    temp.add(row.get(1));
                    result.add(temp);
                }
            }
        }
        return result;
    }
    private void Ban(Integer userID) throws SQLException {
        admin.ban(userID);
    }

    private void Remove(Integer userID) throws SQLException {
        admin.unban(userID);
    }
}
