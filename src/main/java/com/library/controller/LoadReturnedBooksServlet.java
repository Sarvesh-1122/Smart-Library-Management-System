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

@WebServlet("/loadReturnedBooks")
public class LoadReturnedBooksServlet extends HttpServlet{
	private IssueBookService issueBookService = new IssueBookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<IssueBook> issueBooks = issueBookService.getAllReturnedBooks();

        request.setAttribute("issueBooks", issueBooks);

        RequestDispatcher rd = request.getRequestDispatcher("viewReturnedBooks.jsp");
        rd.forward(request, response);
    }

}
