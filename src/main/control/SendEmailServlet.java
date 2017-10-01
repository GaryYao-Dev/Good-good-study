package main.control;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.mail.Authenticator;

import com.sun.mail.util.MailSSLSocketFactory;
import main.JDBC.Addfriendemail;


@WebServlet(name = "SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //specific friend
        String con_add = (String)request.getSession().getAttribute("con_add");
        String user_name = (String)request.getSession().getAttribute("user_name");
        List<String> specificlist = new ArrayList();
        String specific_name = (String) request.getSession().getAttribute("specific_name");
        String specific_email = (String) request.getSession().getAttribute("specific_email");
        String all_name = (String) request.getSession().getAttribute("all_name");
        String all_email = (String) request.getSession().getAttribute("all_email");
        String advanced_name = (String) request.getSession().getAttribute("advanced_name");
        String advanced_email = (String) request.getSession().getAttribute("advanced_email");
        Addfriendemail ad = new Addfriendemail();

        if (con_add == "right" && specific_email != null && specific_name != null){
            ad.Sendspecificemail(user_name, specific_name ,specific_email);

        }
        if (con_add == "right" && all_email != null && all_name != null){
            ad.Sendallemail(user_name, all_name ,all_email);

        }
        if (con_add == "right" && advanced_email != null && advanced_name != null){
            ad.Sendadvancedemail(user_name, advanced_name ,advanced_email);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
