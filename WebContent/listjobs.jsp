<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Jobs</title>
</head>
<body>

   <sql:setDataSource   url="jdbc:oracle:thin:@localhost:1521:xe"
      user="hr" password="hr"  driver="oracle.jdbc.driver.OracleDriver"
      var="con"   />

   <sql:query var="jobs"  dataSource="${con}">
        select * from jobs order by job_title 
   </sql:query>
   
   <c:forEach  var="job" items="${jobs.rows}">
        ${job.job_title}
        <br/>
   </c:forEach>
   
</body>
</html>