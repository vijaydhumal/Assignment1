<%@ page import="java.util.Date" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Page</title>
</head>
<body>
       <%-- VIEW LAYER --%>
       
      <h1 style="color:red">Welcome To Application</h1>
      <h2>Login Successful</h2>
      <% 
       String user=(String)request.getAttribute("user");
       String dob=(String)request.getAttribute("dob");
       String pno=(String)request.getAttribute("pno");
       String address=(String)request.getAttribute("address");
       Date currentDate=new Date();
       String currentTime=currentDate.toString();
      %>
      <h1>Username :<%=user %></h1>
      <h1>Date Of Birth :<%=dob %></h1>
      <h1>Phone Number :<%=pno %></h1> 
      <h1>Current Time :<%= currentTime %></h1>
      <h1>Adress :<%=address %></h1>     

</body>
</html>