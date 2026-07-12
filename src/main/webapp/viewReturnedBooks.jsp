<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.library.dto.IssueBook"%>

<%
List<IssueBook> issueBooks=(List<IssueBook>)request.getAttribute("issueBooks");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Returned Books</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/table.css">

</head>

<body>

	<div class="table-container">

		<h2>📚 Returned Books</h2>

		<table>

			<thead>

				<tr>

					<th>Issue ID</th>

					<th>Student Name</th>

					<th>Roll No</th>

					<th>Book Name</th>

					<th>Issue Date</th>

					<th>Expected Return</th>

					<th>Actual Return</th>

					<th>Fine (₹)</th>

				</tr>

			</thead>

			<tbody>

				<%
				if (issueBooks != null && !issueBooks.isEmpty()) {

					for (IssueBook issueBook : issueBooks) {
				%>

				<tr>

					<td><%=issueBook.getIssueId()%></td>

					<td><%=issueBook.getStudentName()%></td>

					<td><%=issueBook.getRollNo()%></td>

					<td><%=issueBook.getBookName()%></td>

					<td><%=issueBook.getIssueDate()%></td>

					<td><%=issueBook.getReturnDate()%></td>

					<td><%=issueBook.getActualReturnDate()%></td>

					<td>₹ <%=issueBook.getFineAmount()%></td>

				</tr>

				<%
				}

				} else {
				%>

				<tr class="empty-row">

					<td colspan="8">No Returned Books Found</td>

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