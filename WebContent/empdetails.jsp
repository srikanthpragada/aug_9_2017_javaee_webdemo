<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
</head>
<body>

<h1>Employee Details</h1>

<form action="empdetails.jsp">
  Employee id : <input type="text" name="id" value="${param.id}"/>
  <input type="submit" value="Details"/>
</form>

<%
  String id = request.getParameter("id");
  if ( id == null)
	return;
%>

<jsp:useBean class="beans.EmployeeBean" scope="page" id="emp" />
<jsp:setProperty property="*" name="emp"/>

<%
   emp.load();
 /*
   if ( emp.getName() == null)
   {
	   out.println("<h2>Employee Not Found</h2>");
	   return;
   }
 */
%>

<h2>${emp.info}</h2>


</body>
</html>