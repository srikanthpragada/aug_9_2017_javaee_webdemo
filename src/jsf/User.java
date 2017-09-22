package jsf;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean
public class User {

	private String loginName, password, message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Size(min = 4, max = 10, message = "Must be between 4 to 10 chars")
	@Pattern(regexp = "[0-9a-zA-Z]+", message = "Login Name must contains A-Z, a-z and 0-9 only")
	@NotNull
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Size(min = 4, max = 8, message = "Must be between 4 to 8 chars")
	@NotNull
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	// Action Controller 
	public String login() {

		   if ( loginName.equals("admin") && password.equals("admin"))
			   return "home";  // home.xhtml
		   else {
			   message = "Invalid Login!";
			   return "login";  // login.xhtml
		   }
	}

}
