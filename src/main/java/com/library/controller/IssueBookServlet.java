package com.library.controller;

import java.io.IOException;
import java.sql.Date;

import com.library.service.IssueBookService;
import com.library.dto.IssueBook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/issueBookForm")
public class IssueBookServlet extends HttpServlet {
	
	private IssueBookService issueBookService = new IssueBookService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Date issueDate = Date.valueOf(request.getParameter("issueDate"));
		Date returnDate = Date.valueOf(request.getParameter("returnDate"));
		
		IssueBook  issueBook = new IssueBook();
		
		issueBook.setUserId(userId);
		issueBook.setBookId(bookId);
		issueBook.setIssueDate(issueDate);
		issueBook.setReturnDate(returnDate);
		issueBook.setStatus("ISSUED");
		
		boolean isIssued = issueBookService.issueBook(issueBook);
		
		HttpSession session = request.getSession();
		
		if(isIssued){
			response.sendRedirect("loadAdminDashboard");
		} 
		else{
		    session.setAttribute("error", issueBookService.getErrorMessage());
		    response.sendRedirect("loadIssueBook");
		}
		
	} 
	
	

}
