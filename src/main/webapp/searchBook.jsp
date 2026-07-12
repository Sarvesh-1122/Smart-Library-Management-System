<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Book</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

</head>
<body>

	<h2>Search Book</h2>

	<form action="searchBookForm" method="get">

		<label>Book Name</label> 
		<input type="text" name="bookName" placeholder="Enter Book Name" required>   <br><br>
		
		<input type="submit" value="Search">

	</form>																				<br><br>
	
	<a href="loadAdminDashboard">Back to Dashboard</a>

</body>
</html>