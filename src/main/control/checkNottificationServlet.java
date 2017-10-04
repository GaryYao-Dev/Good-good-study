
package main.control;
import main.JDBC.anyNotificationNotRead;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class checkNottificationServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        int userid=(int)request.getSession().getAttribute("userid");
        String flag=request.getParameter("flag");
        if (anyNotificationNotRead.anyNotificationNotRead(userid)){
            String message="new notification";
            System.out.println(message);
            response.setContentType("test/plain");
            response.getWriter().write(message);

        }
        else{
            String message="no new notification";
            System.out.println(message);
            response.setContentType("test/plain");
            response.getWriter().write(message);

        }
    }

}
