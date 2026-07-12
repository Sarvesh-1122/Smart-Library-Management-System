package com.library.service;

import java.sql.Date;
import java.util.List;

import com.library.dao.BookDAO;
import com.library.dao.IssueBookDAO;
import com.library.dto.IssueBook;

public class IssueBookService {
	private IssueBookDAO issueBookDAO = new IssueBookDAO();
	
	private BookDAO bookDAO = new BookDAO();
	
	private String errorMessage;
	
	
	
	public String getErrorMessage() {
	    return errorMessage;
	}
	
	
	public boolean issueBook(IssueBook issueBook) {
	    if(issueBook == null) {
	        return false;
	    }
	    
	    int availableQuantity = bookDAO.getAvailableQuantity(issueBook.getBookId());

	    if (availableQuantity <= 0) {
	        errorMessage = "Book is not available.";
	        return false;
	    }
	    
	    if(issueBookDAO.isBookAlreadyIssued(issueBook.getUserId(), issueBook.getBookId())) {
	        errorMessage = "This book is already issued to this student.";
	        return false;
	    }

	    
	    return issueBookDAO.issueBook(issueBook);
	}
	
	
	public List<IssueBook> getAllIssuedBooks(){
		return issueBookDAO.getAllIssuedBooks();
	}
	
	
	public List<IssueBook> getAllReturnedBooks() {
	    return issueBookDAO.getAllReturnedBooks();
	}
	
	
	
	
	
	public boolean returnBook(int issueId) {

		int bookId = getBookId(issueId);

	    if (bookId == 0) {
	        errorMessage = "Invalid Issue Id.";
	        return false;
	    }

	    Date returnDate = getReturnDate(issueId);

	    if (returnDate == null) {
	        errorMessage = "Return Date Not Found.";
	        return false;
	    }

	    Date actualReturnDate = getCurrentDate();

	    double fineAmount = calculateFine(returnDate, actualReturnDate);

	    IssueBook issueBook = new IssueBook();

	    issueBook.setIssueId(issueId);
	    issueBook.setBookId(bookId);
	    issueBook.setActualReturnDate(actualReturnDate);
	    issueBook.setFineAmount(fineAmount);

	    return issueBookDAO.returnBook(issueBook);
	}
	
	
	
	
	
	public int getTotalIssuedBooks() {
	    return issueBookDAO.getTotalIssuedBooks();
	}
	
	
	public int getTotalReturnedBooks() {
	    return issueBookDAO.getTotalReturnedBooks();
	}
	
	
	
	
	public List<IssueBook> getMyIssuedBooks(int userId){
		return issueBookDAO.getMyIssuedBooks(userId);
	}
	
	
	public List<IssueBook> getMyReturnedBooks(int userId){
	    return issueBookDAO.getMyReturnedBooks(userId);
	}
	
	
	
	
	
	
	//Helper method
	private int getBookId(int issueId) {
	    return issueBookDAO.getBookId(issueId);
	}

	private Date getReturnDate(int issueId) {
	    return issueBookDAO.getReturnDate(issueId);
	}
	
	private double calculateFine(Date returnDate, Date actualReturnDate) {

		long difference = actualReturnDate.getTime() - returnDate.getTime();
		
		long lateDays = difference / (1000 * 60 * 60 * 24);
		
		if (lateDays <= 0) {
		return 0;
		}
		
		return lateDays * 10;
	}

	
	private Date getCurrentDate() {
	    return new Date(System.currentTimeMillis());
	}
	
	
	
	
	

}
