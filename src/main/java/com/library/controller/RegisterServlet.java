package com.library.controller;

import java.io.IOException;

import com.library.dto.User;
import com.library.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerForm")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String rollNo = request.getParameter("rollNo");

		User user = new User();

		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setPassword(password);
		user.setRole(role);
		user.setRollNo(rollNo);

		boolean result = userService.registerUser(user);

		if(result){
			response.sendRedirect("login.jsp");
		}
		else{
			response.sendRedirect("register.jsp");
		}
		
		
	}

}
