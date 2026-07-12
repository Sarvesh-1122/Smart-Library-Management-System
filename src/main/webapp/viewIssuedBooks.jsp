<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.library.dto.IssueBook"%>

<%
List<IssueBook> issueBooks=(List<IssueBook>)request.getAttribute("issueBooks");

String success=(String)session.getAttribute("success");
String error=(String)session.getAttribute("error");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Issued Books</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/table.css">

</head>

<body>

	<div class="table-container">

		<h2>📚 Issued Books</h2>

		<%
		if (success != null) {
		%>

		<div class="success">

			<%=success%>

		</div>

		<%
		session.removeAttribute("success");
		}
		%>

		<%
		if (error != null) {
		%>

		<div class="error">

			<%=error%>

		</div>

		<%
		session.removeAttribute("error");
		}
		%>

		<table>

			<thead>

				<tr>

					<th>Issue ID</th>

					<th>Student Name</th>

					<th>Roll No</th>

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

					<td><%=issueBook.getStudentName()%></td>

					<td><%=issueBook.getRollNo()%></td>

					<td><%=issueBook.getBookName()%></td>

					<td><%=issueBook.getIssueDate()%></td>

					<td><%=issueBook.getReturnDate()%></td>

					<td><a
						href="returnBookForm?issueId=<%=issueBook.getIssueId()%>">

							Return </a></td>

				</tr>

				<%
				}

				} else {
				%>

				<tr class="empty-row">

					<td colspan="7">No Issued Books Found</td>

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