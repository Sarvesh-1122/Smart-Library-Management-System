package com.library.controller;

import java.io.IOException;
import java.util.List;

import com.library.dto.Book;
import com.library.service.BookService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loadSearchBook")
public class LoadSearchBookServlet extends HttpServlet{
	
	BookService bookService = new BookService();
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    RequestDispatcher rd = request.getRequestDispatcher("searchBook.jsp");
	    rd.forward(request, response);
    }
}
