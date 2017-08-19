<%@ page language="java" 
    contentType="text/html; charset=ISO-8859-1"
    import="java.util.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Namese</title>
</head>
<body>

<h2>Names</h2>
<form action="names.jsp">
 Name : <input type="text" name="name" value="${param.name}" />
 <input type="submit" value="Add" />
</form>

<%

  LinkedHashSet<String> names  = (LinkedHashSet<String>) session.getAttribute("names");
  if(names == null)
  {
	  names = new LinkedHashSet<String>();
	  session.setAttribute("names", names);
  }

  String name = request.getParameter("name");
  if (name != null) {
	  names.add(name);  // add name to collection 
  }
  
%>

<p/>
<ul>
    
<%  
  for(String n : names)
	  out.println( "<li>" + n + "</li>");
 
%>

</ul>


</body>
</html>