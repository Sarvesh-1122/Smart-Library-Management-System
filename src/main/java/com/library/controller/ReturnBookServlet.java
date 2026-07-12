package com.library.controller;

import java.io.IOException;

import com.library.service.IssueBookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/returnBookForm")
public class ReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IssueBookService issueBookService = new IssueBookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	int issueId = Integer.parseInt(request.getParameter("issueId"));
        
        boolean isReturned = issueBookService.returnBook(issueId);
        
        HttpSession session = request.getSession();
        
        if(isReturned){
            session.setAttribute("success", "Book Returned Successfully.");
            response.sendRedirect("myIssuedBooks");
        } 
        else{
            session.setAttribute("error", "Unable to Return Book.");
            response.sendRedirect("myIssuedBooks");
        }
    }

}
