package jaxws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.jws.WebMethod;
import javax.jws.WebService;

import oracle.jdbc.rowset.OracleCachedRowSet;

@WebService
public class JobsService {

	//  0 - Success, 1 - Id not found, 2 - Updation failed 
	@WebMethod
	public int updateJob(String id, String newTitle) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			PreparedStatement ps = con.prepareStatement
					  ("update jobs set job_title = ? where job_id = ?");
			ps.setString(1,newTitle);
			ps.setString(2, id);

			int count = ps.executeUpdate();
			ps.close();
			con.close();
			
			if (count == 1)
				return 0; // Success 
			else
			   return 1;  // Id is not found 
			
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
			return 2;  // Error 
		}
	}
	
	// Returns Job object with job details if id is found otherwise it returns null
	// Takes job id as parameter 
	@WebMethod
	public Job getJob(String id) {
		try(OracleCachedRowSet rs = new OracleCachedRowSet()) {
			rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rs.setUsername("hr");
			rs.setPassword("hr");

			rs.setCommand("select * from jobs where upper(job_id) = ?");
			rs.setString(1, id.toUpperCase());
			rs.execute();
			
			if ( rs.next() )
			{
				Job job = new Job();
				job.setId(rs.getString("job_id"));
				job.setTitle(rs.getString("job_title"));
				job.setMinSal(rs.getInt("min_salary"));
				job.setMaxSal(rs.getInt("max_salary"));
				return job; 
			}
			else
				return null;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
		 
	}
}
