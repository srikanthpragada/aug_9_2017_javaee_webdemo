package jsf;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean 
public class HelloBean {
	
	public HelloBean() {
		System.out.println("HelloBean()");
	}
	// Property message 
	public String getMessage() {
		System.out.println("getMessage()");
		return  new Date().toString();
	}

}
