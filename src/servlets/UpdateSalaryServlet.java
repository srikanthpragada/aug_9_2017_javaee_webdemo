package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateSalary")
public class UpdateSalaryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		try {
			// 1. Load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. connect to oracle
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");

			// 3. update salary
			Statement st = con.createStatement();

			int count = st.executeUpdate("update employees set salary = salary * 1.1 where employee_id = 550");
			if (count == 1)
				pw.println("Updated Salary Successfully!");
			else
				pw.println("Sorry! Employee Id Not Found!");

			// 4. close
			con.close();
		} catch (Exception ex) {
			pw.println("Error : " + ex.getMessage());
		}
	}

}
