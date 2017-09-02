package mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SendMailAttachmentServlet", urlPatterns = {"/file"})
public class SendMailAttachmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
 
        String to = "james@java.com";
        String from = "gavin@java.com";
        String subject = "Ship";
        String body = "Ship.....";
        String filename = "ship.jpg";
        

        Properties props = System.getProperties();
        
        Authenticator auth = new Authenticator(){
            @Override 
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gavin@java.com","g");
            }
        };
        
        // authentication required 
        props.setProperty("mail.smtp.auth","true");
        Session session = Session.getDefaultInstance(props,auth);
                
        try {
            
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            msg.setSubject(subject);
            // create and fill the first message part
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(body);
            
            // create the second message part
            MimeBodyPart mbp2 = new MimeBodyPart();
            String filepath =  this.getServletContext().getRealPath("/");
            filepath += "/" + filename;
            
            // System.out.println("File path : " + filepath);
            FileDataSource fds = new FileDataSource(filepath);
            // attach the file to the message
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(fds.getName());
            
            // create the Multipart and its parts to it
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            mp.addBodyPart(mbp2);
            
            msg.setContent(mp);  // add the Multipart to the message
            
            msg.setSentDate(new Date());
            Transport.send(msg);
            out.println("<h4>Mail has been sent with attachment.</h4>"); 
        } catch (Exception ex) {
            ex.printStackTrace(out);
        }

    }

}
