<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.library.dto.User"%>
<%@ page session="true"%>

<%
User user=(User)session.getAttribute("user");

if(user==null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Student Dashboard</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/dashboard.css">

</head>

<body>

	<div class="dashboard">

		<div class="header">

			<h1>📚 Smart Library Management System</h1>

			<h2>
				Welcome,
				<%=user.getName()%></h2>

		</div>

		<div class="cards">

			<div class="card">

				<h3>Email</h3>

				<p style="font-size: 18px;">
					<%=user.getEmail()%>
				</p>

			</div>

			<div class="card">

				<h3>Roll Number</h3>

				<p>
					<%=user.getRollNo()%>
				</p>

			</div>

		</div>

		<div class="actions">

			<a href="loadStudentBooks"> 📚 View Available Books </a> <a
				href="myIssuedBooks"> 📖 My Issued Books </a> <a
				href="myReturnedBooks"> ✅ My Returned Books </a> <a
				href="changePassword.jsp"> 🔒 Change Password </a> <a
				href="logoutForm"> 🚪 Logout </a>

		</div>

	</div>

</body>
</html>