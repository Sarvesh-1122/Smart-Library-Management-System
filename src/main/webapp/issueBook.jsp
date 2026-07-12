<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.List"%>
<%@page import="com.library.dto.User"%>
<%@page import="com.library.dto.Book"%>

<%
	List<User> students = (List<User>) request.getAttribute("students");

	List<Book> books = (List<Book>) request.getAttribute("books");
%>
<%
	String error = (String) session.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Issue Book</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

</head>
<body>

	<%
	if (error != null) {
	%>
	
	<p style="color:red;">
	    <%= error %>
	</p>
	
	<%
	session.removeAttribute("error");
	}
	%>

	<form action="issueBookForm" method="post">
	
		<label>Student</label> 
		<select name="userId"> 
			<%
			for (User student : students) {
			%>

			<option value="<%=student.getUserId()%>"> <%=student.getRollNo()%> </option>

			<%
			}
			%>
		</select>                              <br><br>
		
		
		
		<label>Book</label> 
		<select name="bookId">
			<%
			for (Book book : books) {
			%>

			<option value="<%=book.getBookId()%>"> <%=book.getBookName()%> </option>

			<%
			}
			%>
		</select>                               <br><br>
		
		
		
		<label>Issue Date</label>
		<input type="date" name="issueDate" required>     <br><br>
		
		<label>Return Date</label>
		<input type="date" name="returnDate" required>       <br><br>
		
		<input type="submit" value="Issue Book">


	</form>													<br><br>
	
	<a href="loadAdminDashboard">Back to Dashboard</a>


</body>
</html>