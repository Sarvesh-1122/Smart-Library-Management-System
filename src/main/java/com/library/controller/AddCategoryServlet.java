package com.library.controller;

import java.io.IOException;

import com.library.dto.Category;
import com.library.dto.User;
import com.library.service.CategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addCategory")
public class AddCategoryServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private CategoryService categoryService = new CategoryService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Step-1 : Check Session
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		// Step-2 : Get Logged-in User
		User user = (User) session.getAttribute("user");

		// Step-3 : Only Admin Can Add Category
		if (!user.getRole().equalsIgnoreCase("Admin")) {
			response.sendRedirect("studentDashboard.jsp");
			return;
		}

		// Step-4 : Get Form Data
		String categoryName = request.getParameter("categoryName");

		// Step-5 : Create DTO Object
		Category category = new Category();
		category.setCategoryName(categoryName);

		// Step-6 : Call Service
		boolean isAdded = categoryService.addCategory(category);

		// Step-7 : Redirect
		if(isAdded) {
			response.sendRedirect("loadAdminDashboard");
		} 
		else {
			response.sendRedirect("category.jsp");
		}
		
	}

}
