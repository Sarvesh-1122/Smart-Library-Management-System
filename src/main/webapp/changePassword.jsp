<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.library.dto.User"%>

<%
User user=(User)session.getAttribute("user");

if(user==null){
    response.sendRedirect("login.jsp");
    return;
}

String success=(String)session.getAttribute("success");
String error=(String)session.getAttribute("error");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Change Password</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

</head>

<body>

<div class="container">

    <h1>📚 Smart Library</h1>

    <h2>Change Password</h2>

    <% if(success!=null){ %>

        <div class="success">

            <%=success%>

        </div>

    <% session.removeAttribute("success"); } %>

    <% if(error!=null){ %>

        <div class="error">

            <%=error%>

        </div>

    <% session.removeAttribute("error"); } %>

    <form action="changePassword" method="post">

        <label>Current Password</label>

        <input
            type="password"
            name="currentPassword"
            placeholder="Enter Current Password"
            required>

        <label>New Password</label>

        <input
            type="password"
            name="newPassword"
            placeholder="Enter New Password"
            required>

        <label>Confirm Password</label>

        <input
            type="password"
            name="confirmPassword"
            placeholder="Confirm New Password"
            required>

        <input
            type="submit"
            value="Change Password">

    </form>

    <div class="form-footer">

        <a class="back-btn"
           href="studentDashboard.jsp">

            ⬅ Back to Dashboard

        </a>

    </div>

</div>

</body>

</html>