<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.library.dto.User"%>

<%
User user = (User) session.getAttribute("user");

if (user == null) {
    response.sendRedirect("login.jsp");
    return;
}

if (!user.getRole().equalsIgnoreCase("ADMIN")) {
    response.sendRedirect("studentDashboard.jsp");
    return;
}
%>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin DashBoard</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/dashboard.css">

</head>
<body>
	

	<h1>Welcome Admin</h1>
	<h2>Library Management System</h2>
	
	<h3>Total Students : <%= request.getAttribute("totalStudents") %></h3>
	
	<h3>Total Books : <%= request.getAttribute("totalBooks") %></h3>
	
	<h3>Total Issued Books : <%= request.getAttribute("totalIssuedBooks") %></h3>
	
	<h3>Total Returned Books : <%= request.getAttribute("totalReturnedBooks") %></h3>
	
	<h3>Available Books : <%= request.getAttribute("availableBooks") %></h3>
	
	
	
	<hr>

	
	<a href="loadCategory">Add Category</a><br><br>
	
	<a href="loadAddBook">Add Book</a><br><br>
	
	<a href="viewBooks">View Books</a><br><br>
	
	<a href="loadIssueBook">Issue Book</a><br><br>
	
	<a href="loadIssuedBooks">View Issued Books</a><br><br>
	
	<a href="loadSearchBook">Search Book</a><br><br>
	
	<a href="loadReturnedBooks">View Returned Books</a><br><br>
	
	<a href="logoutForm">Logout</a>

</body>
</html>