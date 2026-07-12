package com.library.controller;

import java.io.IOException;

import java.util.List;

import com.library.dto.Book;
import com.library.dto.User;
import com.library.service.BookService;
import com.library.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loadIssueBook")
public class LoadIssueBookServlet extends HttpServlet{
	private UserService userService = new UserService();
	private BookService bookService = new BookService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> students = userService.getAllStudents();
		List<Book> books = bookService.getAllBooks();
		
		request.setAttribute("students", students);
		request.setAttribute("books", books);
		
		RequestDispatcher rd = request.getRequestDispatcher("issueBook.jsp");
		rd.forward(request, response);
		
	}

}
