<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Student Registration</title>

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">

</head>

<body>

<div class="container">

    <h1>📚 Smart Library</h1>

    <h2>Student Registration</h2>

    <form action="registerForm" method="post">

        <label>Full Name</label>

        <input
            type="text"
            name="name"
            placeholder="Enter Full Name"
            required>

        <label>Email</label>

        <input
            type="email"
            name="email"
            placeholder="Enter Email"
            required>

        <label>Mobile Number</label>

        <input
            type="text"
            name="mobile"
            placeholder="Enter Mobile Number"
            required>

        <label>Password</label>

        <input
            type="password"
            name="password"
            placeholder="Enter Password"
            required>

        <label>Roll Number</label>

        <input
            type="text"
            name="rollNo"
            placeholder="Enter Roll Number"
            required>

        <label>Role</label>

        <select name="role">

            <option value="STUDENT">

                Student

            </option>

        </select>

        <input
            type="submit"
            value="Register">

    </form>

    <div class="form-footer">

        <a href="login.jsp">

            Already have an account? Login

        </a>

    </div>

</div>

</body>

</html>