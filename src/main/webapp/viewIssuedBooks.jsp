<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List"%>
<%@ page import="com.library.dto.IssueBook"%>

<%
	List<IssueBook> issueBooks = (List<IssueBook>) request.getAttribute("issueBooks");
%>


<%
	String success = (String) session.getAttribute("success");
	if(success != null){
%>

	<p style="color:green;"><%=success%> </p>

<%
	session.removeAttribute("success");
	}
%>



<%
	String error = (String) session.getAttribute("error");
	if(error != null){
%>

	<p style="color:red;"> <%=error%> </p>

<%
	session.removeAttribute("error");
	}
%>





<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Issued Book</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/table.css">

</head>
<body>
	<h2>Issued Books</h2>

	<table border="1">

		<tr>
			<th>Issue ID</th>
			<th>Student Name</th>
			<th>Roll No</th>
			<th>Book Name</th>
			<th>Issue Date</th>
			<th>Return Date</th>
			<th>Action</th>
		</tr>


		<%
		for (IssueBook issueBook : issueBooks) {
		%>
		<tr>
			<td><%=issueBook.getIssueId()%></td>
			<td><%=issueBook.getStudentName()%></td>
			<td><%=issueBook.getRollNo()%></td>
			<td><%=issueBook.getBookName()%></td>
			<td><%=issueBook.getIssueDate()%></td>
			<td><%=issueBook.getReturnDate()%></td>
			<td><a href="returnBookForm?issueId=<%=issueBook.getIssueId()%>">Return</a></td>
		</tr>
		<%
		}
		%>

	</table>                <br><br>
	
	<a href="loadAdminDashboard">Back to Dashboard</a>
</body>
</html>