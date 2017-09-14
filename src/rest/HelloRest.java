package rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloRest {
	
	@GET 
	public String get() {
		return "Hello RESTFul";
	}

}
