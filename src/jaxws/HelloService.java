package jaxws;

import java.time.LocalDate;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService 
public class HelloService {
	
	 @WebMethod 
	 public String getMessage() {
		 return "Hello From Metro";
	 }

	 
	 @WebMethod 
	 public String getNow() {
		 return LocalDate.now().toString();
	 }

}
