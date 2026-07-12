package com.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.dto.IssueBook;
import com.library.util.DBConnection;

public class IssueBookDAO {
	private static final String ISSUE_BOOK = "INSERT INTO issue_book(user_id, book_id, issue_date, return_date, status) VALUES (?, ?, ?, ?, ?)";

	private static final String DECREASE_AVAILABLE_QUANTITY = "UPDATE books SET available_quantity = available_quantity - 1 WHERE book_id = ?";
	
	private static final String CHECK_ALREADY_ISSUED = "SELECT * FROM issue_book WHERE user_id=? AND book_id=? AND status='ISSUED'";
	
	private static final String GET_ALL_ISSUED_BOOKS = "SELECT ib.issue_id, u.name AS student_name, u.roll_no, b.book_name, ib.issue_date, ib.return_date "+
		                                            	    "FROM issue_book ib JOIN users u " +
	                                             		"ON ib.user_id = u.user_id JOIN books b " +
	                                             		"ON ib.book_id = b.book_id " +
		                                             	"WHERE ib.status='ISSUED'";
	
	private static final String GET_BOOK_ID = "SELECT book_id FROM issue_book WHERE issue_id = ?";
	
//	private static final String RETURN_BOOK = "UPDATE issue_book SET status='RETURNED', actual_return_date=?, fine_amount=? WHERE issue_id=?";
//	
	private static final String RETURN_BOOK = "UPDATE issue_book SET status='RETURNED', actual_return_date=?, fine_amount=? WHERE issue_id=?";
	
	
	private static final String INCREASE_AVAILABLE_QUANTITY = "UPDATE books SET available_quantity = available_quantity + 1 WHERE book_id=?";
	
	private static final String GET_TOTAL_ISSUED_BOOKS = "SELECT COUNT(*) FROM issue_book WHERE status='ISSUED'";
	
	private static final String GET_TOTAL_RETURNED_BOOKS = "SELECT COUNT(*) FROM issue_book WHERE status='RETURNED'";
	
	private static final String GET_MY_ISSUED_BOOKS = "SELECT ib.issue_id, b.book_name, ib.issue_date, ib.return_date " +
													  "FROM issue_book ib JOIN books b " +
													  "ON ib.book_id = b.book_id " +
													  "WHERE ib.user_id=? AND ib.status='ISSUED'";
	
	private static final String GET_MY_RETURNED_BOOKS = "SELECT ib.issue_id, b.book_name, ib.issue_date, ib.return_date, ib.actual_return_date, ib.fine_amount " +
														"FROM issue_book ib JOIN books b " +
														"ON ib.book_id = b.book_id " +
														"WHERE ib.user_id=? AND ib.status='RETURNED'";

	
	//**
	private static final String GET_ALL_RETURNED_BOOKS = "SELECT ib.issue_id, u.name AS student_name, u.roll_no, b.book_name, " +
														 "ib.issue_date, ib.return_date, ib.actual_return_date, ib.fine_amount " +
														 "FROM issue_book ib " +
														 "JOIN users u ON ib.user_id = u.user_id " +
														 "JOIN books b ON ib.book_id = b.book_id " +
														 "WHERE ib.status='RETURNED'";
	
	//private static final String UPDATE_FINE = "UPDATE issue_book SET fine_amount=? WHERE issue_id=?";
	
	private static final String GET_RETURN_DATE = "SELECT return_date FROM issue_book WHERE issue_id=?";
	
//	private static final String UPDATE_ACTUAL_RETURN_DATE = "UPDATE issue_book SET actual_return_date=? WHERE issue_id=?";
	
	
	
	
	
	
	public boolean issueBook(IssueBook issueBook) {
		boolean isIssued = false;

	    Connection con = null;
	    PreparedStatement ps1 = null;
	    PreparedStatement ps2 = null;

	    try{
	        con = DBConnection.getConnection();
	        con.setAutoCommit(false); // Start transaction
	        
	        ps1 = con.prepareStatement(ISSUE_BOOK);
	        ps2 = con.prepareStatement(DECREASE_AVAILABLE_QUANTITY);

	        // parameters
	        ps1.setInt(1, issueBook.getUserId());
	        ps1.setInt(2, issueBook.getBookId());
	        ps1.setDate(3, issueBook.getIssueDate());
	        ps1.setDate(4, issueBook.getReturnDate());
	        ps1.setString(5, issueBook.getStatus());
	        
	        int x = ps1.executeUpdate();
	        
	        ps2.setInt(1, issueBook.getBookId());
	        
	        int y = ps2.executeUpdate();

	        if (x>0 && y>0) {
	        	con.commit(); // Commit transaction if both operations succeed
	            isIssued = true;
	        }else {
	        	con.rollback(); // Rollback transaction if either operation fails
	        }

	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    } 
	    finally{
	        try {
	            if(ps1 != null)
	                ps1.close();
	            if(ps2 != null)
	            	ps2.close();
	            if(con != null)
	                con.close();
	        } 
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return isIssued;
	}
	
	
	public boolean isBookAlreadyIssued(int userId, int bookId) {

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try{
	    	con = DBConnection.getConnection();
	    	ps = con.prepareStatement(CHECK_ALREADY_ISSUED);
	    	
	    	ps.setInt(1, userId);
	    	ps.setInt(2, bookId);
	    	
	    	rs = ps.executeQuery();
	    	
	    	if(rs.next()) {
	    		return true; // Book is already issued to the user
	    	}
	    } 
	    catch (Exception e){
	        e.printStackTrace();
	    } 
	    finally{
	        try{
	            if (rs != null)
	                rs.close();
	            if (ps != null)
	                ps.close();
	            if (con != null)
	                con.close();
	        } 
	        catch (Exception e){
	            e.printStackTrace();
	        }
	        
	    }

	    return false;
	}
	
	
	public List<IssueBook> getAllIssuedBooks(){
		
		List<IssueBook> issueBooks = new ArrayList<>();

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	    	con = DBConnection.getConnection();
	    	ps = con.prepareStatement(GET_ALL_ISSUED_BOOKS);
	    	rs = ps.executeQuery();

	    	while(rs.next()) {
	    	    IssueBook issueBook = new IssueBook();
	    	    
	    	    issueBook.setIssueId(rs.getInt("issue_id"));
	    	    issueBook.setStudentName(rs.getString("student_name"));
	    	    issueBook.setRollNo(rs.getString("roll_no"));
	    	    issueBook.setBookName(rs.getString("book_name"));
	    	    issueBook.setIssueDate(rs.getDate("issue_date"));
	    	    issueBook.setReturnDate(rs.getDate("return_date"));
	    	    
	    	    issueBooks.add(issueBook);
	    	}
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    } 
	    finally {
	        try {
	            if (rs != null)
	                rs.close();
	            if (ps != null)
	                ps.close();
	            if (con != null)
	                con.close();
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return issueBooks;
	}
	

	public int getBookId(int issueId) {

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try{
	    	    con = DBConnection.getConnection();
	    	    ps = con.prepareStatement(GET_BOOK_ID);
	    	    
	    	    ps.setInt(1, issueId);
	            rs = ps.executeQuery();
	            if(rs.next()) {
	                return rs.getInt("book_id");
	            }
	    } 
	    catch(Exception e) {
	        e.printStackTrace();
	    } 
	    finally{
	        try{
	            if (rs != null)
	                rs.close();
	            if (ps != null)
	                ps.close();
	            if (con != null)
	                con.close();
	        } 
	        catch(Exception e){
	            e.printStackTrace();
	        }
	    }

	    return 0;
	}
	

	public boolean returnBook(IssueBook issueBook) {

	    boolean isReturned = false;

	    Connection con = null;
	    PreparedStatement ps1 = null;
	    PreparedStatement ps2 = null;

	    try {

	        con = DBConnection.getConnection();
	        con.setAutoCommit(false);

	        // 1. Update issue_book table
	        ps1 = con.prepareStatement(RETURN_BOOK);

	        ps1.setDate(1, issueBook.getActualReturnDate());
	        ps1.setDouble(2, issueBook.getFineAmount());
	        ps1.setInt(3, issueBook.getIssueId());

	        int x = ps1.executeUpdate();

	        // 2. Increase available quantity
	        ps2 = con.prepareStatement(INCREASE_AVAILABLE_QUANTITY);

	        ps2.setInt(1, issueBook.getBookId());

	        int y = ps2.executeUpdate();

	        // 3. Commit or Rollback
	        if (x > 0 && y > 0) {
	            con.commit();
	            isReturned = true;
	        } 
	        else {
	            con.rollback();
	        }
	    } 
	    catch (Exception e) {
	        try {
	            if (con != null) {
	                con.rollback();
	            }
	        } 
	        catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } 
	    finally {
	        try {
	            if (ps1 != null)
	                ps1.close();
	            if (ps2 != null)
	                ps2.close();
	            if (con != null)
	                con.close();
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return isReturned;
	}
	
	
	
	public int getTotalIssuedBooks() {

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {

	        con = DBConnection.getConnection();

	        ps = con.prepareStatement(GET_TOTAL_ISSUED_BOOKS);

	        rs = ps.executeQuery();

	        if(rs.next()) {
	            return rs.getInt(1);
	        }
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {

	        try {
	            if (rs != null)
	                rs.close();
	            if (ps != null)
	                ps.close();
	            if (con != null)
	                con.close();
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return 0;
	}
	
	
	public int getTotalReturnedBooks() {

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {

	        con = DBConnection.getConnection();

	        ps = con.prepareStatement(GET_TOTAL_RETURNED_BOOKS);

	        rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1);
	        }
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    } 
	    finally {
	        try {
	            if (rs != null)
	                rs.close();
	            if (ps != null)
	                ps.close();
	            if (con != null)
	                con.close();
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return 0;
	}
	
	
	public List<IssueBook> getMyIssuedBooks(int userId) {

	    List<IssueBook> issueBooks = new ArrayList<>();

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try{
	    	con = DBConnection.getConnection();
	    	ps = con.prepareStatement(GET_MY_ISSUED_BOOKS);
	    	ps.setInt(1, userId);
	    	rs = ps.executeQuery();
	    	
	    	while (rs.next()) {

	    	    IssueBook issueBook = new IssueBook();

	    	    issueBook.setIssueId(rs.getInt("issue_id"));
	    	    issueBook.setBookName(rs.getString("book_name"));
	    	    issueBook.setIssueDate(rs.getDate("issue_date"));
	    	    issueBook.setReturnDate(rs.getDate("return_date"));

	    	    issueBooks.add(issueBook);
	    	}

	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    } 
	    finally {
	        try {
	            if (rs != null)
	                rs.close();
	            if (ps != null)
	                ps.close();
	            if (con != null)
	                con.close();
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return issueBooks;
	}
	
	
	public List<IssueBook> getMyReturnedBooks(int userId){
		List<IssueBook> issuedBooks = new ArrayList<>();

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try{
	    	con = DBConnection.getConnection();
	    	ps = con.prepareStatement(GET_MY_RETURNED_BOOKS);
	    	ps.setInt(1, userId);
	    	rs = ps.executeQuery();
	    	
	    	while (rs.next()) {

	    	    IssueBook issueBook = new IssueBook();

	    	    issueBook.setIssueId(rs.getInt("issue_id"));
	    	    issueBook.setBookName(rs.getString("book_name"));
	    	    issueBook.setIssueDate(rs.getDate("issue_date"));
	    	    issueBook.setReturnDate(rs.getDate("return_date"));
	    	    issueBook.setActualReturnDate(rs.getDate("actual_return_date"));
	    	    issueBook.setFineAmount(rs.getDouble("fine_amount"));

	    	    issuedBooks.add(issueBook);
	    	}

	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    } 
	    finally {
	        try {
	            if (rs != null)
	                rs.close();
	            if (ps != null)
	                ps.close();
	            if (con != null)
	                con.close();
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return issuedBooks;
		
	}
	
	
	public Date getReturnDate(int issueId) {

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = DBConnection.getConnection();

	        ps = con.prepareStatement(GET_RETURN_DATE);
	        ps.setInt(1, issueId);

	        rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getDate("return_date");
	        }
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    } 
	    finally {
	        try {
	            if (rs != null)
	                rs.close();
	            if (ps != null)
	                ps.close();
	            if (con != null)
	                con.close();
	        } 
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return null;
	}
	
	
	public List<IssueBook> getAllReturnedBooks() {

	    List<IssueBook> issueBooks = new ArrayList<>();

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	    	con = DBConnection.getConnection();

	    	ps = con.prepareStatement(GET_ALL_RETURNED_BOOKS);

	    	rs = ps.executeQuery();

	    	while (rs.next()) {

	    	    IssueBook issueBook = new IssueBook();

	    	    issueBook.setIssueId(rs.getInt("issue_id"));
	    	    issueBook.setStudentName(rs.getString("student_name"));
	    	    issueBook.setRollNo(rs.getString("roll_no"));
	    	    issueBook.setBookName(rs.getString("book_name"));

	    	    issueBook.setIssueDate(rs.getDate("issue_date"));
	    	    issueBook.setReturnDate(rs.getDate("return_date"));

	    	    issueBook.setActualReturnDate(rs.getDate("actual_return_date"));
	    	    issueBook.setFineAmount(rs.getDouble("fine_amount"));

	    	    issueBooks.add(issueBook);
	    	}
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    finally {
	        try {
	            if (rs != null)
	                rs.close();
	            if (ps != null)
	                ps.close();
	            if (con != null)
	                con.close();
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return issueBooks;
	}
	
	
	
	
}
