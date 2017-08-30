package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class JobsTagHandler extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException {
		JspWriter writer = this.getJspContext().getOut();
		try {
			writer.println("<h1>Jobs</h1>");

			OracleCachedRowSet rs = new OracleCachedRowSet();
			rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rs.setUsername("hr");
			rs.setPassword("hr");

			rs.setCommand("select * from jobs");
			rs.execute();

			writer.println(
					"<table border='1' cellpadding='5px'><tr><th>Id </th><th>Title</th><th>Min Salary </th></tr>");
			while (rs.next()) {
				writer.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", rs.getString("job_id"),
						rs.getString("job_title"), rs.getString("min_salary")));
			}
			writer.println("</table>");
			rs.close();
		} catch (Exception ex) {

		}
	}
}
