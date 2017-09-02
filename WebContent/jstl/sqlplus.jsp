<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
    <body bgcolor="#dddddd">
        <form action="sqlplus.jsp" method="post">
            SQL Query
            <br/>
            <textarea  cols="80" name="query"  ROWs="5">${param.query}</textarea>
            <p/>
            <input type=submit value=Execute>
        </form>

        <%
            String query = request.getParameter("query");
            if (query == null || query.length() == 0) {
                return;
            }
        %>

        <sql:setDataSource
            var="oracle"
            driver="oracle.jdbc.driver.OracleDriver"
            url="jdbc:oracle:thin:@localhost:1521:XE"  
            user="hr"
            password="hr"
            />
        <c:catch var="ex">
            <sql:query  var="result" dataSource="${oracle}">
                ${param.query}
            </sql:query>
        </c:catch>

       
        <%
            Exception ex = (Exception) pageContext.getAttribute("ex");
            if (ex != null) {
                out.println(ex.getMessage());
                return;
            }
        %>

        <table border="1" cellpadding="3" width="100%">
            <tr>
                <c:forEach  var="colname"  items="${result.columnNames}">
                    <th>${colname}</th>
                </c:forEach>
            </tr> 

            <c:forEach  var="row"  items="${result.rowsByIndex}">
                <tr>
                    <c:forEach  var="colvalue"  items="${row}">
                        <td>
                           ${colvalue}
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>

