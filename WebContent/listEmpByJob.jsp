<%@ page language="java" 
    contentType="text/html; charset=ISO-8859-1"
    import="oracle.jdbc.rowset.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees By Job</title>
</head>
<body>
<h1>List Employees By Job</h1>
<form action="listEmpByJob.jsp">
   Job <input value="${param.job}" type="text" name="job"  required="required" />
   <input type="submit" value="Employees" />
</form>
<p></p>
<%
  String job = request.getParameter("job");
  if(job == null)
	  return;
  
  OracleCachedRowSet rs = new OracleCachedRowSet();
  rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
  rs.setUsername("hr");
  rs.setPassword("hr");
  
  rs.setCommand("select * from employees where job_id  = ?");
  rs.setString(1, job);
  
  rs.execute();
 %>
 
 <table border="1" width="100%">
   <tr><th>Name </th><th>Salary </th> <th> Department </th> </tr>

 <%
  while(rs.next()) {
	  out.println("<tr><td>" +  rs.getString("first_name") + " " + rs.getString("last_name") 
			  +  "</td><td>" + 
                rs.getInt("salary") + "</td><td>" + rs.getInt("department_id") + "</td></tr>");
  }
  rs.close();
 %>
 </table>


</body>
</html>