package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.dto.Book;
import com.library.util.DBConnection;

public class BookDAO {
	private static final String INSERT_BOOK = "INSERT INTO books(book_name,auther,category_id,publisher,total_quantity,available_quantity) VALUES(?,?,?,?,?,?)";

	private static final String GET_ALL_BOOKS = "SELECT b.book_id, b.book_name, b.auther, c.category_name, b.publisher, b.total_quantity, b.available_quantity FROM books b JOIN category c ON b.category_id = c.category_id";
	
	private static final String GET_AVAILABLE_QUANTITY = "SELECT available_quantity FROM books WHERE book_id = ?";
	
	private static final String SEARCH_BOOK = "SELECT b.book_id, b.book_name, b.auther, c.category_name, b.publisher, b.total_quantity, b.available_quantity " +
											 "FROM books b JOIN category c " +
											 "ON b.category_id = c.category_id " +
											 "WHERE b.book_name LIKE ?";
	
	private static final String GET_TOTAL_BOOKS = "SELECT COUNT(*) FROM books";
	
	private static final String GET_AVAILABLE_BOOKS = "SELECT SUM(available_quantity) FROM books";
	
	
	
	public boolean addBook(Book book) {
        boolean isAdded = false;

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(INSERT_BOOK);
            
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getCategoryId());
            ps.setString(4, book.getPublisher());
            ps.setInt(5, book.getQuantity());
            ps.setInt(6, book.getQuantity());
            
            int x = ps.executeUpdate();
            
            if (x > 0) {
                isAdded = true;
            }

        } 
        catch(Exception e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if (ps != null)
                    ps.close();
                if (con != null)
                    con.close();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        return isAdded;
    }
	
	
	
	public List<Book> getAllBooks(){

		List<Book> books = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(GET_ALL_BOOKS);
			rs = ps.executeQuery();
			
			while (rs.next()) {

				Book book = new Book();

				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				book.setAuthor(rs.getString("auther"));
				book.setCategoryName(rs.getString("category_name"));
				book.setPublisher(rs.getString("publisher"));
				book.setQuantity(rs.getInt("total_quantity"));
				book.setAvailableQuantity(rs.getInt("available_quantity"));

				books.add(book);
			}

		} 
		catch(Exception e){
			e.printStackTrace();
		} 
		finally{
			try{
				if(rs != null)
					rs.close();

				if(ps != null)
					ps.close();

				if(con != null)
					con.close();

			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return books;
	}
	
	
	public int getAvailableQuantity(int bookId) {
		 Connection con = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;

		    try{
		    	con = DBConnection.getConnection();
		    	ps = con.prepareStatement(GET_AVAILABLE_QUANTITY);
		    	
		    	ps.setInt(1, bookId);
		    	
		    	rs = ps.executeQuery();
		    	
		    	if(rs.next()) {
		    		return rs.getInt("available_quantity");
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
	
	
	public List<Book> searchBook(String bookName){

	    List<Book> books = new ArrayList<>();

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try{
	    		con = DBConnection.getConnection();
	    		ps = con.prepareStatement(SEARCH_BOOK);
	    		
	    		ps.setString(1, "%" + bookName + "%");

	    		rs = ps.executeQuery();
	    		
	    		while (rs.next()) {

	    	        Book book = new Book();

	    	        book.setBookId(rs.getInt("book_id"));
	    	        book.setBookName(rs.getString("book_name"));
	    	        book.setAuthor(rs.getString("auther"));
	    	        book.setCategoryName(rs.getString("category_name"));
	    	        book.setPublisher(rs.getString("publisher"));
	    	        book.setQuantity(rs.getInt("total_quantity"));
	    	        book.setAvailableQuantity(rs.getInt("available_quantity"));

	    	        books.add(book);
	    	    }
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    finally{
	        try{
	            if(rs != null)
	                rs.close();
	            if(ps != null)
	                ps.close();
	            if(con != null)
	                con.close();
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	    }

	    return books;
	}
	
	
	public int getTotalBooks() {
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try{
	        con = DBConnection.getConnection();

	        ps = con.prepareStatement(GET_TOTAL_BOOKS);

	        rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1);
	        }

	    } catch (Exception e) {
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
	
	
	public int getAvailableBooks() {

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {

	        con = DBConnection.getConnection();

	        ps = con.prepareStatement(GET_AVAILABLE_BOOKS);

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
	
}
