package jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class JobsService {

	@WebMethod
	public void updateJob(String id, String newTitle) {
		
	}
	
	@WebMethod
	public Job getJob(String id) {
		
		return null; 
	}
}
