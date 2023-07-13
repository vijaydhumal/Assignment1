<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Page</title>
</head>
<body>
      <%
      int value=(int) request.getAttribute("values");
      
      %>
      <%=value %>
      <h1>RECORD INSERTED SUCCESSFULLY</h1>
      
</body>
</html>