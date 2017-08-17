<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Leap Year</h1>
	<form action="leapyear2.jsp">
		Enter year : <input type="number" name="year" value="${param.year}"  />
		 <input type="submit"	value="Submit" />
	</form>

	<h2>
		<%
			String input = request.getParameter("year");
		
			if (input != null) {
				int year = Integer.parseInt(input);
				if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
					out.println("Is a Leap Year!");
				else
					out.println("Is Not a Leap Year!");
			}
		%>
	</h2>


</body>
</html>