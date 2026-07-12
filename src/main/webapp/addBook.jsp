<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.library.dto.Category"%>

<%
List<Category> categories=(List<Category>)request.getAttribute("categories");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Add Book</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

</head>

<body>

	<div class="container">

		<h1>📚 Smart Library</h1>

		<h2>Add New Book</h2>

		<form action="addBookForm" method="post">

			<label>Book Name</label> <input type="text" name="bookName"
				placeholder="Enter Book Name" required> <label>Author</label>

			<input type="text" name="author" placeholder="Enter Author Name"
				required> <label>Category</label> <select name="categoryId">

				<%
        if(categories!=null){
            for(Category category:categories){
        %>

				<option value="<%=category.getCategoryId()%>">

					<%=category.getCategoryName()%>

				</option>

				<%
            }
        }
        %>

			</select> <label>Publisher</label> <input type="text" name="publisher"
				placeholder="Enter Publisher Name" required> <label>Total
				Quantity</label> <input type="number" name="quantity" min="1" required>

			<input type="submit" value="Add Book">

		</form>

		<div class="form-footer">

			<a class="back-btn" href="loadAdminDashboard"> ⬅ Back to
				Dashboard </a>

		</div>

	</div>

</body>

</html>