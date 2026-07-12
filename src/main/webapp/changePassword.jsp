<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@page import="com.library.dto.User"%>

<%
User user = (User) session.getAttribute("user");

if(user == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<% 
	String success = (String) session.getAttribute("success"); 
	String error = (String) session.getAttribute("error");
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

</head>
<body>

	<h2>Change Password</h2>
	<% if(success != null){ %>
	<%= success %>
	<% session.removeAttribute("success"); %>
	<%} %>
	
	<% if(error != null){ %>
    <%= error %>
    <% session.removeAttribute("error"); %>
    <%} %>
	
	<form action="changePassword" method="post">

	    <label>Current Password</label><br>
	    <input type="password" name="currentPassword" required>  <br><br>
	
	    <label>New Password</label><br>
	    <input type="password" name="newPassword" required>   <br><br>
	
	    <label>Confirm Password</label><br>
	    <input type="password" name="confirmPassword" required>    <br><br>
	
	    <input type="submit" value="Change Password">

     </form>                                                      <br><br>

	<a href="studentDashboard.jsp">Back to Dashboard</a>

</body>
</html>