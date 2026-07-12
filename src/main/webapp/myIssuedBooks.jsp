<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.library.dto.IssueBook"%>
<%@ page import="com.library.dto.User"%>

<%
User user=(User)session.getAttribute("user");

if(user==null){
    response.sendRedirect("login.jsp");
    return;
}

if(!user.getRole().equalsIgnoreCase("STUDENT")){
    response.sendRedirect("loadAdminDashboard");
    return;
}

List<IssueBook> issueBooks=(List<IssueBook>)request.getAttribute("issueBooks");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>My Issued Books</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/table.css">

</head>

<body>

	<div class="table-container">

		<h2>📚 My Issued Books</h2>

		<table>

			<thead>

				<tr>

					<th>Issue ID</th>

					<th>Book Name</th>

					<th>Issue Date</th>

					<th>Return Date</th>

					<th>Action</th>

				</tr>

			</thead>

			<tbody>

				<%
				if (issueBooks != null && !issueBooks.isEmpty()) {

					for (IssueBook issueBook : issueBooks) {
				%>

				<tr>

					<td><%=issueBook.getIssueId()%></td>

					<td><%=issueBook.getBookName()%></td>

					<td><%=issueBook.getIssueDate()%></td>

					<td><%=issueBook.getReturnDate()%></td>

					<td><a
						href="returnBookForm?issueId=<%=issueBook.getIssueId()%>">

							Return Book </a></td>

				</tr>

				<%
				}

				} else {
				%>

				<tr class="empty-row">

					<td colspan="5">No Issued Books Found</td>

				</tr>

				<%
				}
				%>

			</tbody>

		</table>

		<br> <a class="back-btn" href="studentDashboard.jsp"> ⬅ Back
			to Dashboard </a>

	</div>

</body>

</html>