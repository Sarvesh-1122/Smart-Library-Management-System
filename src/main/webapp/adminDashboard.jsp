<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

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
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/dashboard.css">

</head>

<body>

<div class="dashboard">

    <div class="header">

        <h1>📚 Smart Library Management System</h1>

        <h2>Welcome, Admin</h2>

    </div>


    <div class="cards">

        <div class="card">

            <h3>Total Students</h3>

            <p><%=request.getAttribute("totalStudents")%></p>

        </div>

        <div class="card">

            <h3>Total Books</h3>

            <p><%=request.getAttribute("totalBooks")%></p>

        </div>

        <div class="card">

            <h3>Issued Books</h3>

            <p><%=request.getAttribute("totalIssuedBooks")%></p>

        </div>

        <div class="card">

            <h3>Returned Books</h3>

            <p><%=request.getAttribute("totalReturnedBooks")%></p>

        </div>

        <div class="card">

            <h3>Available Books</h3>

            <p><%=request.getAttribute("availableBooks")%></p>

        </div>

    </div>


    <div class="actions">

        <a href="loadCategory">📂 Add Category</a>

        <a href="loadAddBook">📚 Add Book</a>

        <a href="viewBooks">📖 View Books</a>

        <a href="loadIssueBook">📝 Issue Book</a>

        <a href="loadIssuedBooks">📋 Issued Books</a>

        <a href="loadReturnedBooks">✅ Returned Books</a>

        <a href="loadSearchBook">🔍 Search Book</a>

        <a href="logoutForm">🚪 Logout</a>

    </div>

</div>

</body>
</html>