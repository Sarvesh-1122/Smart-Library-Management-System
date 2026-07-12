<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Registration</title>

<link rel="styleSheet" href="css/style.css">
<link rel="stylesheet" href="css/form.css">


</head>
<body>

	<div class="container">

		<h2>Student Registration</h2>

		<form action="registerForm" method="post">


			<label>Name</label> <input type="text" name="name"
				placeholder="Enter Name"> <label>Email</label> <input
				type="email" name="email" placeholder="Enter Email"> <label>Mobile</label>
			<input type="text" name="mobile" placeholder="Enter Mobile Number">


			<label>Password</label> <input type="password" name="password"
				placeholder="Enter Password"> <label>Roll Number</label> 
			<input type="text" name="rollNo" placeholder="Enter Roll Number"> 

			
			<label>Role</label>
			<select name="role">
				<option value="STUDENT">Student</option>
			</select> 
			
			<input type="submit" value="Register">


		</form>

	</div>


</body>
</html>