package com.library.controller;

import java.io.IOException;
import java.util.List;

import com.library.dto.IssueBook;
import com.library.service.IssueBookService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/loadIssuedBooks")
public class LoadIssuedBooksServlet extends HttpServlet {
	
	private IssueBookService issueBookService = new IssueBookService();
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<IssueBook> issueBooks = issueBookService.getAllIssuedBooks();
		
		request.setAttribute("issueBooks", issueBooks);
		
		RequestDispatcher rd = request.getRequestDispatcher("viewIssuedBooks.jsp");
		rd.forward(request, response);
		
    }

}
