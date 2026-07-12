<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%@page import="java.util.List"%>
<%@page import="com.library.dto.Category"%>

<%
   List<Category> categories = (List<Category>)request.getAttribute("categories");
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book</title>
<link rel="styleSheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

</head>
<body>
	<div class="container">
		<h2>Add New Book</h2>
		
		<form action="addBookForm" method="post">

			<label>Book Name</label> 
			<input type="text" name="bookName" placeholder="Enter Book Name" required> <br><br>
			
			<label>Author</label>
			<input type="text" name="author" placeholder="Enter Author Name" required> <br><br>
			
			
			<select name="categoryId">
				<%
				for (Category category : categories) {
				%>

				<option value="<%=category.getCategoryId()%>"> <%=category.getCategoryName()%> </option> <br><br>

				<%
				}
				%>
			</select> 
			
			
			<label>Publisher</label> 
			<input type="text" name="publisher" placeholder="Enter Publisher Name" required>  <br><br>
			
			<label>Quantity</label>
			<input type="number" name="quantity" min="1" required> <br><br>
			
			<input type="submit" value="Add Book">

		</form>														<br><br>

		<a href="loadAdminDashboard">Back to Dashboard</a>

	</div>

</body>
</html>