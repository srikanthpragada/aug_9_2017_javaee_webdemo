package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class Jobs3TagHandler extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException {
		JspWriter writer = this.getJspContext().getOut();
		PageContext ctx = (PageContext) this.getJspContext();
		JspFragment body = this.getJspBody();
		
		try {

			OracleCachedRowSet rs = new OracleCachedRowSet();
			rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rs.setUsername("hr");
			rs.setPassword("hr");

			rs.setCommand("select * from jobs");
			rs.execute();


			while (rs.next()) {
                   ctx.setAttribute("id",  rs.getString("job_id"));
                   ctx.setAttribute("title",  rs.getString("job_title"));
                   ctx.setAttribute("minSalary",  rs.getString("min_salary"));
                   ctx.setAttribute("maxSalary",  rs.getString("max_salary"));
                   
                   // process body and send output to writer 
                   body.invoke(writer);
                   
			}
			
			rs.close();
		} catch (Exception ex) {

		}
	}
}
