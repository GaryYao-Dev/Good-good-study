package comp9321;

import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

public class SendEmail 
{
    public static void sendmail(String emailadd,String username) throws GeneralSecurityException 
    {

        String to = emailadd;
        String from = "807308796@qq.com";
        String host = "smtp.qq.com"; 
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("807308796@qq.com", "mbtifkdstlgrbeid"); //发件人邮件用户名、密码
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Confirm mail");
            message.setText("Dear user "+username+" \nThis is a e-mail to confirm your registration on our website\nIf you want to confirm your registration,please use this url in your browser.http://localhost:8080/confirm.jsp?username="+username);
            Transport.send(message);
     
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public static void sendmail1(String emailadd,String ss) throws GeneralSecurityException
    {

        String to = emailadd;
        String from = "807308796@qq.com";
        String host = "smtp.qq.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("807308796@qq.com", "mbtifkdstlgrbeid"); //发件人邮件用户名、密码
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Confirm mail");
            message.setText(ss);
            Transport.send(message);

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
//	public static void main(String args[]) {
//		try {
//			sendmail("807308796@qq.com");
//		} catch (GeneralSecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}
}
