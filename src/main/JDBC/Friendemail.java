package main.JDBC;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Friendemail {

    public static void SendUNSWemail(String username, String friend_name,String email) {

        String target_mail_add = email;
        final String from = "unswbookemailserver@gmail.com";
        final String password = "root123@";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("unswbookemailserver@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(target_mail_add));
            message.setSubject("Testing Subject");
            message.setText("Dear user "+friend_name+": \nThis is a e-mail to confirm add friennd request on our website\nIf you want to have a look of the person,please use this url in your browser.http://localhost:8080/emailconfirm.jsp?applyname="+username+"&username="+friend_name+"");
            Transport.send(message);

            System.out.println("Done");

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

//    public static void main(String[] args) {
//
//        SendUNSWemail("vigoss", "Wangjie","wangjiecrystal0@gmail.com");
//    }


}
