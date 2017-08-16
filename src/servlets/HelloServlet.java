package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// @WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	int count = 1;
	String message; 
	@Override
	public void init(ServletConfig config) {
	   System.out.println("init()");
	   message = config.getInitParameter("message");
	   
	}
	
	@Override 
	public void destroy() {
		   System.out.println("destroy()");	
	}
	
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
		  PrintWriter pw = response.getWriter();
		  
		  String name = request.getParameter("name");
		  if(name == null)
			   name = "World!";
		  
		  pw.print("<h1>Hello " + name +  "</h1>");
		  pw.print("<hr>");
		  pw.print("<h2>" +  message   + "</h3>");
		  pw.print("<h3>" + count   + "</h3>");
		  count ++;
			
	}

}
