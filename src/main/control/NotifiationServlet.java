package main.control;

import javax.servlet.http.HttpSession;
import javafx.geometry.Pos;
import main.JDBC.DButil;
import main.JDBC.PostMessage;
import main.JDBC.SendNotification;
import main.JDBC.likeMessage;
import main.model.postMessageBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;

public class NotifiationServlet extends HttpServlet{
    public void notifia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userid = (Integer) session.getAttribute("userid");
        List<String> result= SendNotification.find(userid);
        session.setAttribute("postMessageList",result);

        request.getRequestDispatcher("/notification.jsp").forward(request,response);






    }
}
