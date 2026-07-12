package com.library.service;

import java.util.List;

import com.library.dao.BookDAO;
import com.library.dto.Book;

public class BookService {
	private BookDAO bookDAO = new BookDAO();

	public boolean addBook(Book book) {

		// Book Name Validation
		if (book.getBookName() == null || book.getBookName().trim().isEmpty()) {
			return false;
		}

		// Author Validation
		if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
			return false;
		}

		// Publisher Validation
		if (book.getPublisher() == null || book.getPublisher().trim().isEmpty()) {
			return false;
		}

		// Quantity Validation
		if (book.getQuantity() <= 0) {
			return false;
		}

		// Category Validation
		if (book.getCategoryId() <= 0) {
			return false;
		}

		return bookDAO.addBook(book);

	}
	
	
	public List<Book> getAllBooks() {
	    return bookDAO.getAllBooks();
	}
	
	
	public List<Book> searchBook(String bookName){
		return bookDAO.searchBook(bookName);
	}
	
	
	public int getTotalBooks() {
	    return bookDAO.getTotalBooks();
	}
	
	
	public int getAvailableBooks() {
		return bookDAO.getAvailableBooks();
	}

}
