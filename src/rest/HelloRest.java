package rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/hello")
public class HelloRest {
	
	@GET 
	public String get() {
		return "Hello Restful Services";
	}
	
	@GET 
	@Path("/{name}")
	public String get( @PathParam("name") String name) {
		return "Hello " + name;
	}

}
