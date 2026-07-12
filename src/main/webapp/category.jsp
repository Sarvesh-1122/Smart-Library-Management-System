<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.library.dto.User"%>

<%
User user = (User) session.getAttribute("user");

if (user == null) {
	response.sendRedirect("login.jsp");
	return;
}

if (!user.getRole().equalsIgnoreCase("Admin")) {
	response.sendRedirect("studentDashboard.jsp");
	return;
}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Category</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

</head>
<body>

	<div class="container">

		<h2>Add New Category</h2>

		<form action="addCategory" method="post">

			<label>Category Name</label> 
			<input type="text" name="categoryName" placeholder="Enter Category Name" required> <br> <br>
			
			<input type="submit" value="Add Category">

		</form><br> 
		
		<a href="loadAdminDashboard">⬅ Back to Dashboard</a>

	</div>

</body>
</html>