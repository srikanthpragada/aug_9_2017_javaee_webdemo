package rest;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import oracle.jdbc.rowset.OracleCachedRowSet;

@Path("/employees")
public class EmpService {
	
	@GET 
	public String getAllEmployees() {

		try(OracleCachedRowSet rs = new OracleCachedRowSet()) {
			rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rs.setUsername("hr");
			rs.setPassword("hr");

			rs.setCommand("select employee_id, first_name, last_name, salary, to_char(hire_date,'dd-MON-yyyy') hire_date from employees");
			rs.execute();
			
			JsonArrayBuilder emps = Json.createArrayBuilder();

			while (rs.next()) {
				JsonObjectBuilder jobBuilder = Json.createObjectBuilder();
				jobBuilder.add("id", rs.getString("employee_id"));
				jobBuilder.add("name", rs.getString("first_name") +  " " + rs.getString("last_name"));
				jobBuilder.add("salary", rs.getString("salary"));
				jobBuilder.add("hiredate", rs.getString("hire_date"));
				emps.add(jobBuilder);
			}

			rs.close();

			return emps.build().toString(); 
		}
		catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		
	}
	
	@GET 
	@Path("/{dept}")
	public String getEmployeesByDept( @PathParam("dept") String dept) {
		try(OracleCachedRowSet rs = new OracleCachedRowSet()) {
			rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rs.setUsername("hr");
			rs.setPassword("hr");

			rs.setCommand("select employee_id, first_name, last_name, salary, to_char(hire_date,'dd-MON-yyyy') hire_date from employees where department_id = ?");
			rs.setString(1, dept);
			
			rs.execute();
			
			JsonArrayBuilder emps = Json.createArrayBuilder();

			while (rs.next()) {
				JsonObjectBuilder jobBuilder = Json.createObjectBuilder();
				jobBuilder.add("id", rs.getString("employee_id"));
				jobBuilder.add("name", rs.getString("first_name") +  " " + rs.getString("last_name"));
				jobBuilder.add("salary", rs.getString("salary"));
				jobBuilder.add("hiredate", rs.getString("hire_date"));
				emps.add(jobBuilder);
			}

			rs.close();

			return emps.build().toString(); 
		}
		catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

}
