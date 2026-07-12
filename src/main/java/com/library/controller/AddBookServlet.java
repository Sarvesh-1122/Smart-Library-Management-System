package com.library.controller;

import java.io.IOException;

import com.library.dto.Book;
import com.library.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addBookForm")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		Book book = new Book();

		book.setBookName(bookName);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setCategoryId(categoryId);
		book.setQuantity(quantity);

		boolean isAdded = bookService.addBook(book);

		if(isAdded){
			response.sendRedirect("loadAdminDashboard");
		} 
		else{
			response.sendRedirect("loadAddBookForm");
		}

	}

}
