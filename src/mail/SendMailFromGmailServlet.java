package mail;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Security;
import java.util.Properties;
import javax.mail.Message;
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

@WebServlet(name = "SendFromGmailServlet", urlPatterns = {"/sendfromgmail"})
public class SendMailFromGmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String SMTP_HOST_NAME = "smtp.gmail.com";
        String SMTP_PORT = "465"; // or use 567
        String toAddress = request.getParameter("toaddress");
        final String username = request.getParameter("username");
        final String fromAddress = username + "@gmail.com";
        final String password = request.getParameter("password");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");
      
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            Security.addProvider(
                    new com.sun.net.ssl.internal.ssl.Provider());
            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST_NAME);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.socketFactory.fallback", "false");
            
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                       @Override
                       protected PasswordAuthentication
                        getPasswordAuthentication() {
                            return new PasswordAuthentication
                                           (username, password);
                        }
                    });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromAddress));
            msg.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(toAddress));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
            out.println("<h4>Mail has been sent Successfully!</h4>");
        } catch (Exception ex) {

            ex.printStackTrace(out);
        }

    }

}
