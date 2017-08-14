package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
		// receive parameters
		String fullname = request.getParameter("fullname");
		String feedback = request.getParameter("feedback");
		
		System.out.println(fullname);
		System.out.println(feedback);
		
		response.setContentType("text/html");
		response.getWriter().println("<h2>Thank You!</h2>");
	}

}
