<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Login</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

</head>

<body>

<div class="container">

    <h1>📚 Smart Library</h1>

    <h2>Login</h2>

    <form action="loginForm" method="post">

        <label>Email</label>

        <input
            type="email"
            name="email"
            placeholder="Enter Email"
            required>

        <label>Password</label>

        <input
            type="password"
            name="password"
            placeholder="Enter Password"
            required>

        <input
            type="submit"
            value="Login">

    </form>

    <div class="form-footer">

        <a href="register.jsp">

            New Student? Register Here

        </a>

    </div>

</div>

</body>

</html>