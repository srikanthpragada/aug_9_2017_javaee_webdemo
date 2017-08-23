package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/oracle")
public class OracleConnectionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		try {
			// 1. Load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. connect to oracle
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			pw.println("Connected To Oracle using Thin Driver");
			
			// 3. close
			con.close();
		} catch (Exception ex) {
			pw.println(ex.getMessage());
		}
	}

}
