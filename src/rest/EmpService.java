package rest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

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
	@Path("/{id}")
	public String getEmployee( @PathParam("id") String id) {
		try(OracleCachedRowSet rs = new OracleCachedRowSet()) {
			rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rs.setUsername("hr");
			rs.setPassword("hr");

			rs.setCommand("select employee_id, first_name, last_name, salary, to_char(hire_date,'dd-MON-yyyy') hire_date from employees where employee_id = ?");
			rs.setString(1, id);
			rs.execute();
			

			if (rs.next()) {
				JsonObjectBuilder builder = Json.createObjectBuilder();
				builder.add("id", rs.getString("employee_id"));
				builder.add("name", rs.getString("first_name") +  " " + rs.getString("last_name"));
				builder.add("salary", rs.getString("salary"));
				builder.add("hiredate", rs.getString("hire_date"));
				return builder.build().toString();
			}
			else
				throw new NotFoundException();
		}
		catch(SQLException ex) {
			System.out.println(ex);
			throw new InternalServerErrorException();
		}
	}


	
	@GET 
	@Path("/dept/{dept}")
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

	
	@PUT 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/{id}")
	public void updateSalary(@PathParam("id") String id,
			 @FormParam("newSalary") int newSalary) {
		
		try {
			// 1. Load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. connect to oracle
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");

			// 3. update salary
			PreparedStatement ps = con.prepareStatement(
					"update employees set salary = ? where employee_id = ?");
			
			ps.setInt(1,newSalary);
			ps.setString(2,id);
			
			int count = ps.executeUpdate();
			
			if (count == 0)
				throw new NotFoundException();
			
			// 4. close
			con.close();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new InternalServerErrorException();
		}
		
		
	}

	
	@DELETE 
	@Path("/{id}")
	public void deleteEmployee(@PathParam("id") String id) {
		try {
			// 1. Load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. connect to oracle
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");

			// 3. update salary
			PreparedStatement ps = con.prepareStatement(
					"delete from employees where employee_id = ?");
			
			ps.setString(1,id);
			
			int count = ps.executeUpdate();
			
			if (count == 0)
				throw new NotFoundException();
			
			// 4. close
			con.close();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new InternalServerErrorException();
		}
		
		
	}
	
	
	
	
}


