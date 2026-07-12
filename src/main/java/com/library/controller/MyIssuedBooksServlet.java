package com.library.controller;

import java.io.IOException;
import java.util.List;

import com.library.dto.IssueBook;
import com.library.dto.User;
import com.library.service.IssueBookService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/myIssuedBooks")
public class MyIssuedBooksServlet extends HttpServlet{
	private IssueBookService issueBookService = new IssueBookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	
    	int userId = user.getUserId();
    	
    	List<IssueBook> issueBooks = issueBookService.getMyIssuedBooks(userId);
    	
    	request.setAttribute("issueBooks", issueBooks);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("myIssuedBooks.jsp");
    	rd.forward(request, response);
    }

}
