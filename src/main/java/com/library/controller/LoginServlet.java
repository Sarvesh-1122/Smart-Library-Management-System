package com.library.controller;

import java.io.IOException;

import com.library.dto.User;
import com.library.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginForm")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userService.loginUser(email, password);

		if(user != null) {

			HttpSession session = request.getSession();

			session.setAttribute("user", user);

			if("ADMIN".equalsIgnoreCase(user.getRole())) {
				response.sendRedirect("loadAdminDashboard");
			} 
			else{
				response.sendRedirect("studentDashboard.jsp");
			}

		} 
		else{
			response.sendRedirect("login.jsp");
		}

	}

}
