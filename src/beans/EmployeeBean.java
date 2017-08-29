package beans;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class EmployeeBean {
	private int id;
	private String name;
	private String job;
	
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public void load() 
	{
		try {
			  OracleCachedRowSet rs = new OracleCachedRowSet();
			  rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			  rs.setUsername("hr");
			  rs.setPassword("hr");
			  
			  rs.setCommand("select first_name, job_title from employees inner join jobs using(job_id) where employee_id  = ?");
			  rs.setInt(1, id);
			  
			  rs.execute();
			  if ( rs.next())  // found 
			  {
				  this.name = rs.getString("first_name");
				  this.job = rs.getString("job_title");
				  this.info = "[" + rs.getString("first_name") + "] is working as [" + rs.getString("job_title") + "]";
			  }
			  else
			  {
				  this.name = null;
				  this.job = null;
				  this.info = "Sorry! Employee Id Not Found";
			  }
			  rs.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			this.name = null;
			this.job = null;
			this.info = "Sorry! Couldn't get details of employee!";
		}
		
	}

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	