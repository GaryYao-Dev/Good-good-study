package main.control;

import main.JDBC.likeMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UnlikePostServlet")
public class UnlikePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取 自身 userid
        HttpSession session =request.getSession();
        int likeUser_id = (int) session.getAttribute("userid");


        int p_id = Integer.parseInt(request.getParameter("p_id"));

        likeMessage.unlikePost(p_id, likeUser_id);
        response.getWriter().write("unlike success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
