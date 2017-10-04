
package main.control;

import main.JDBC.PostMessage;
import main.JDBC.SendNotification;
import main.JDBC.UserInfro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.JDBC.UserInfro.getUseridByUsername;
import static main.JDBC.friendconfirm.confirmfriend;

@WebServlet(name = "FriendConfirmServlet")
public class FriendConfirmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("123");
        String con_email_button = (String) request.getSession().getAttribute("con_email_button");
        System.out.println(con_email_button);
        String useremailname = (String) request.getSession().getAttribute("useremailname");
        System.out.println(useremailname);
        String applyemailname = (String) request.getSession().getAttribute("applyemailname");;
        System.out.println(applyemailname);
        int applyemailid = UserInfro.getUseridByUsername(applyemailname);
        System.out.println(applyemailid);
        int useremailid = UserInfro.getUseridByUsername(useremailname);
        System.out.println(useremailid);

        //给 该 post的用户发 站内信
        String userName = UserInfro.getUserNameByUserid(applyemailid);
        SendNotification.sendnotification(applyemailid,useremailid,userName+" and you now are friend!");

        if (useremailname != null && applyemailname!= null ){
            confirmfriend(applyemailid,useremailid);
            confirmfriend(useremailid,applyemailid);
            response.sendRedirect("/confirm_sucess.jsp");
        }
    }
}
