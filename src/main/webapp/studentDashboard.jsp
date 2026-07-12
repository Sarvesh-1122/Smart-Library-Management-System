<%@ page import="com.library.dto.User"%>
<%@ page session="true"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student DashBoard</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/dashboard.css">

</head>

<body>

	<%
	User user = (User) session.getAttribute("user");

	if (user == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>

	<h1>Welcome Student</h1>

	<h2>Hello <%=user.getName()%></h2>

	<p>Email : <%=user.getEmail()%></p>
	
	<p>Roll No : <%= user.getRollNo() %> </p>
	
	<a href="loadStudentBooks">View Available Books </a>   <br><br>

	<a href="myIssuedBooks">My Issued Books</a>   <br><br>
	
	<a href="myReturnedBooks">My Returned Books</a>   <br><br>
	
	<a href="changePassword.jsp">Change Password</a>   <br><br>
	
	<a href="logoutForm"> Logout</a>

</body>


</html>