<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leap Year</title>
</head>
<body>
	<h1>
	    
		<%
			String input = request.getParameter("year");
		    out.println(input);
			int year = Integer.parseInt(input);
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
				out.println(" Is a Leap Year!");
			else
				out.println(" Is Not a Leap Year!");
		%>
	</h1>

</body>
</html>