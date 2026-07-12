<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.library.dto.IssueBook"%>

<%
    List<IssueBook> issueBooks = (List<IssueBook>) request.getAttribute("issueBooks");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Returned Books</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/table.css">

</head>
<body>

<h2>Returned Books</h2>

<table border="1">

    <tr>
        <th>Issue ID</th>
        <th>Student Name</th>
        <th>Roll No</th>
        <th>Book Name</th>
        <th>Issue Date</th>
        <th>Expected Return Date</th>
        <th>Actual Return Date</th>
        <th>Fine (₹)</th>
    </tr>

<%
if(issueBooks != null && !issueBooks.isEmpty()){
    for(IssueBook issueBook : issueBooks){
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
}
else{
%>

    <tr>
        <td colspan="8">No Returned Books Found.</td>
    </tr>

<%
}
%>

</table>

<br><br>

<a href="loadAdminDashboard">Back to Dashboard</a>

</body>
</html>