package json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.rowset.OracleCachedRowSet;

@WebServlet("/createjobs")
public class CreateJobsJsonServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();

		try {
			OracleCachedRowSet rs = new OracleCachedRowSet();
			rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rs.setUsername("hr");
			rs.setPassword("hr");

			rs.setCommand("select * from jobs");
			rs.execute();

			JsonArrayBuilder jobs = Json.createArrayBuilder();

			while (rs.next()) {
				JsonObjectBuilder jobBuilder = Json.createObjectBuilder();
				jobBuilder.add("id", rs.getString("job_id"));
				jobBuilder.add("title", rs.getString("job_title"));
				jobs.add(jobBuilder);
			}

			rs.close();

			pw.println(jobs.build());

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
