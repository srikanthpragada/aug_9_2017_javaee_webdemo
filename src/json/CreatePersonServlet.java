package json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createperson")
public class CreatePersonServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw  = response.getWriter();
		
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("name", "Stephen");
		builder.add("age", 20);
		pw.println( builder.build());
		
		JsonGenerator  gen = Json.createGenerator(pw);
		gen.writeStartObject()
		   .write("name", "Stephen")
		   .write("age", 20)
		   .writeEnd();
		
		gen.close();
		
		
	}



}
