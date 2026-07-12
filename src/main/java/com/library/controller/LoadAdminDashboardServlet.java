package com.library.controller;

import java.io.IOException;

import com.library.service.BookService;
import com.library.service.IssueBookService;
import com.library.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loadAdminDashboard")
public class LoadAdminDashboardServlet extends HttpServlet{
	private UserService userService = new UserService();
	private BookService bookService = new BookService();
	private IssueBookService issueBookService = new IssueBookService();
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		int totalStudents = userService.getTotalStudents();
    		request.setAttribute("totalStudents", totalStudents);
    		
    		int totalBooks = bookService.getTotalBooks();
    		request.setAttribute("totalBooks", totalBooks);
    		
    		int totalIssuedBooks = issueBookService.getTotalIssuedBooks();
    		request.setAttribute("totalIssuedBooks", totalIssuedBooks);
    		
    		int totalReturnedBooks = issueBookService.getTotalReturnedBooks();
    		request.setAttribute("totalReturnedBooks", totalReturnedBooks);
    		
    		int availableBooks = bookService.getAvailableBooks();
    		request.setAttribute("availableBooks", availableBooks);
    		
    		
    		
    		RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
    		rd.forward(request, response);
    		
    		
    }
	

}
