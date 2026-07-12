<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="com.library.dto.Book"%>
<%@page import="com.library.dto.User"%>


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

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Books</title>
<link rel="StylrSheet" href="css/style.css">
<link rel="stylesheet" href="css/table.css">

</head>
<body>

	<h2>Available Books</h2>

	<table border="1">

		<tr>
			<th>Book Id</th>
			<th>Book Name</th>
			<th>Author</th>
			<th>Category</th>
			<th>Publisher</th>
			<th>Quantity</th>
			<th>Available</th>
		</tr>
		
		<%
		if (books != null && !books.isEmpty()) {
			for (Book book : books) {
		%>

		<tr>
			<td><%=book.getBookId()%></td>
			<td><%=book.getBookName()%></td>
			<td><%=book.getAuthor()%></td>
			<td><%=book.getCategoryName()%></td>
			<td><%=book.getPublisher()%></td>
			<td><%=book.getQuantity()%></td>
			<td><%=book.getAvailableQuantity()%></td>
		</tr>

		<%
		}

		} else {
		%>

		<tr>
			<td colspan="7">No Books Available</td>
		</tr>

		<%
		}
		%>

	</table>									<br><br>
	
	<a href="loadAdminDashboard">Back to Dashboard</a>

</body>
</html>