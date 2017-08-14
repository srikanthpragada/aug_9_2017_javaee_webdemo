package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
		  PrintWriter pw = response.getWriter();
		  
		  String name = request.getParameter("name");
		  if(name == null)
			   name = "World!";
		  
		  pw.print("<h1>Hello " + name +  "</h1>");
		  pw.print("<hr>");
		  pw.print("<h3>" + new Date()  + "</h3>");
			
	}

}
