<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List"%>
<%@ page import="com.library.dto.IssueBook"%>
<%@ page import="com.library.dto.User"%>

<%
	User user = (User) session.getAttribute("user");
	
	if(user == null){
	    response.sendRedirect("login.jsp");
	    return;
	}
	
	if(!user.getRole().equalsIgnoreCase("STUDENT")){
	    response.sendRedirect("loadAdminDashboard");
	    return;
	}
	
	List<IssueBook> issueBooks = (List<IssueBook>) request.getAttribute("issueBooks");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Returned Books</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/table.css">

</head>
<body>
	<h2>My Returned Books</h2>
	<table border="1">

		<tr>
		    <th>Issue Id</th>
		    <th>Book Name</th>
		    <th>Issue Date</th>
		    <th>Expected Return Date</th>
    		<th>Actual Return Date</th>
    		<th>Fine Amount (₹)</th>
		</tr>
		
		<%
		if(issueBooks != null && !issueBooks.isEmpty()){
		    for(IssueBook issueBook : issueBooks){
		%>
				<tr>
				    <td><%=issueBook.getIssueId()%></td>	
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
			    <td colspan="6"> No Returned Books Found. </td>
			</tr>
		<%
		}
		%>
		
	</table>              <br><br>

	<a href="studentDashboard.jsp"> Back to Dashboard </a>
	
	
	

</body>
</html>