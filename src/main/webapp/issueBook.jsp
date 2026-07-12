<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.library.dto.User"%>
<%@ page import="com.library.dto.Book"%>

<%
List<User> students=(List<User>)request.getAttribute("students");
List<Book> books=(List<Book>)request.getAttribute("books");

String error=(String)session.getAttribute("error");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Issue Book</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

</head>

<body>

<div class="container">

    <h1>📚 Smart Library</h1>

    <h2>Issue Book</h2>

    <% if(error!=null){ %>

        <div class="error">

            <%=error%>

        </div>

    <% session.removeAttribute("error"); } %>

    <form action="issueBookForm" method="post">

        <label>Student</label>

        <select name="userId">

        <%
        if(students!=null){
            for(User student:students){
        %>

            <option value="<%=student.getUserId()%>">

                <%=student.getRollNo()%>

            </option>

        <%
            }
        }
        %>

        </select>

        <label>Book</label>

        <select name="bookId">

        <%
        if(books!=null){
            for(Book book:books){
        %>

            <option value="<%=book.getBookId()%>">

                <%=book.getBookName()%>

            </option>

        <%
            }
        }
        %>

        </select>

        <label>Issue Date</label>

        <input
            type="date"
            name="issueDate"
            required>

        <label>Return Date</label>

        <input
            type="date"
            name="returnDate"
            required>

        <input
            type="submit"
            value="Issue Book">

    </form>

    <div class="form-footer">

        <a class="back-btn"
           href="loadAdminDashboard">

            ⬅ Back to Dashboard

        </a>

    </div>

</div>

</body>

</html>