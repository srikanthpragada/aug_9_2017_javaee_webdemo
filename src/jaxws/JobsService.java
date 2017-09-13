package jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import oracle.jdbc.rowset.OracleCachedRowSet;

@WebService
public class JobsService {

	//  0 - Success, 1 - Id not found, 2 - Updation failed 
	@WebMethod
	public int  updateJob(String id, String newTitle) {
		
        return 0;
	}
	
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
