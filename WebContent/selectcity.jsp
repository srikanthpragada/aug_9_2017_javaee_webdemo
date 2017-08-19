

<%
   String city = request.getParameter("city");
   
   Cookie c = new Cookie("city", city);
   c.setMaxAge( 7 * 24 * 60 * 60);
   
   response.addCookie(c);
   
   response.sendRedirect("movies.jsp");


%>