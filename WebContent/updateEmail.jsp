<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="java.sql.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Email</title>
</head>
<body>

<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
	String id = request.getParameter("id");
	String email = request.getParameter("email");
	
	PreparedStatement ps  = con.prepareStatement
			("update employees set email = ? where employee_id  = ?");
	
	ps.setString(1,email);
	ps.setString(2,id);
	
	int count = ps.executeUpdate();
	
	if ( count == 1)
		 out.println("Updated Employee Email Successfully!");
	else
		 out.println("Sorry! Employee Not Found!");


%>

</body>
</html>