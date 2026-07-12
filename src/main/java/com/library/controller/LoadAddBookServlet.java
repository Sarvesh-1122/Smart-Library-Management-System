package com.library.controller;

import java.io.IOException;

import java.util.List;

import com.library.dto.Category;
import com.library.service.CategoryService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loadAddBook")
public class LoadAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryService categoryService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Category> categories = categoryService.getAllCategories();

		request.setAttribute("categories", categories);

		RequestDispatcher rd = request.getRequestDispatcher("addBook.jsp");

		rd.forward(request, response);

	}

}
