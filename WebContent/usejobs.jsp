<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.srikanthtechnologies.com/aug9" prefix="aug9"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Use Job Tags</title>
</head>
<body>

	<aug9:jobs2 title="manager" />
	<ul>
		<aug9:jobs3>
			<li>${title},${minSalary}</li>
		</aug9:jobs3>
	</ul>
</body>
</html>