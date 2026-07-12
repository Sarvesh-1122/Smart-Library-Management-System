<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.library.dto.Book"%>
<%@ page import="com.library.dto.User"%>

<%
User user=(User)session.getAttribute("user");

if(user==null){
    response.sendRedirect("login.jsp");
    return;
}

if(!user.getRole().equalsIgnoreCase("ADMIN")){
    response.sendRedirect("studentDashboard.jsp");
    return;
}

List<Book> books=(List<Book>)request.getAttribute("books");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>View Books</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/table.css">

</head>

<body>

	<div class="table-container">

		<h2>📚 Available Books</h2>

		<table>

			<thead>

				<tr>

					<th>Book ID</th>

					<th>Book Name</th>

					<th>Author</th>

					<th>Category</th>

					<th>Publisher</th>

					<th>Total Qty</th>

					<th>Available</th>

				</tr>

			</thead>

			<tbody>

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

				<tr class="empty-row">

					<td colspan="7">No Books Available</td>

				</tr>

				<%
				}
				%>

			</tbody>

		</table>

		<br> <a class="back-btn" href="loadAdminDashboard"> ⬅ Back to
			Dashboard </a>

	</div>

</body>

</html>