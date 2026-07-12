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

	<div class="container">

		<h1>📚 Smart Library</h1>

		<h2>Search Book</h2>

		<form action="searchBookForm" method="get">

			<label>Book Name</label> <input type="text" name="bookName"
				placeholder="Enter Book Name" required> <input type="submit"
				value="Search Book">

		</form>

		<div class="form-footer">

			<a class="back-btn" href="loadAdminDashboard"> ⬅ Back to
				Dashboard </a>

		</div>

	</div>

</body>

</html>