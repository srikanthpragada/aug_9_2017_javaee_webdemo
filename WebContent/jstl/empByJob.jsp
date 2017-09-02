<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<body>

	<sql:setDataSource var="oracle"
		driver="oracle.jdbc.driver.OracleDriver"
		url="jdbc:oracle:thin:@localhost:1521:XE" user="hr" password="hr" />

	<sql:query var="jobs" dataSource="${oracle}">
        select * from jobs
    </sql:query>

	<form action="empByJob.jsp">
		Select Job <select name="job">
			<c:forEach var="job" items="${jobs.rows}">
				<option   ${param.job == job.job_id ? "SELECTED" :""}  value="${job.job_id}">${job.job_title}</option>
			</c:forEach>
		</select> <input type="submit" value="Employees" />
	</form>


	<sql:query var="employees" dataSource="${oracle}">
               select * from employees where job_id = ?
               <sql:param value="${param.job}"></sql:param>
	</sql:query>


	<table border="1" cellpadding="3" width="100%">
		<tr>
			<c:forEach var="colname" items="${employees.columnNames}">
				<th>${colname}</th>
			</c:forEach>
		</tr>

		<c:forEach var="row" items="${employees.rowsByIndex}">
			<tr>
				<c:forEach var="colvalue" items="${row}">
					<td>${colvalue}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>