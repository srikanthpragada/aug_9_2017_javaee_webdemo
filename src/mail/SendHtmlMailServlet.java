package mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SendHtmlMailServlet", urlPatterns = {"/sendhtmlmail"})
public class SendHtmlMailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String to = request.getParameter("toaddress");
        String from = request.getParameter("fromaddress");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        Properties props = System.getProperties();
        Session session = Session.getDefaultInstance(props, null);
        try {
            // construct the message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            msg.setDataHandler(
                    new DataHandler(body, "text/html"));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            Transport.send(msg);    // send message
            out.println("<h2>Mail was sent successfully</h2>");
        } catch (Exception ex) {
            out.println("<h2>Error --> " + ex.getMessage() + "</h2>");
        }

    }

}
