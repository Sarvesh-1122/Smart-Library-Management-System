package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.dto.User;
import com.library.util.DBConnection;

public class UserDAO {
	private static final String INSERT_USER = "INSERT INTO users(name,email,mobile,password,role,roll_no) VALUES(?,?,?,?,?,?)";

	private static final String LOGIN_USER = "SELECT * FROM users WHERE email=? AND password=?";
	
	private static final String GET_TOTAL_STUDENT = "SELECT COUNT(*) FROM users WHERE role = 'STUDENT'";
	
	private static final String CHANGE_PASSWORD = "UPDATE users SET password=? WHERE user_id=?";
	
	
	
	
	
	public boolean registerUser(User user) {
		boolean isRegistered = false;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_USER);

			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getMobile());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole());
			ps.setString(6, user.getRollNo());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				isRegistered = true;
			}

			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isRegistered;
	}
	
	
	public User loginUser(String email, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try{
			con = DBConnection.getConnection();
			ps = con.prepareStatement(LOGIN_USER);
			ps.setString(1, email);
			ps.setString(2, password);
			
			rs = ps.executeQuery();

			if(rs.next()) {

				User user = new User();

				user.setUserId(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getString("mobile"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setRollNo(rs.getString("roll_no"));

				return user;
			}

		} 
		catch(Exception e) {
			e.printStackTrace();
		} 
		finally{

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
		return null;
	}
	
	
	public List<User> getAllStudents() {

	    List<User> students = new ArrayList<>();

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = DBConnection.getConnection();
	        ps = con.prepareStatement("SELECT * FROM users WHERE role='STUDENT'");
	        rs = ps.executeQuery();

	        while(rs.next()) {
	            User user = new User();

	            user.setUserId(rs.getInt("user_id"));
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            user.setMobile(rs.getString("mobile"));
	            user.setPassword(rs.getString("password"));
	            user.setRole(rs.getString("role"));
	            user.setRollNo(rs.getString("roll_no"));

	            students.add(user);
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
	            System.out.println("Connection closed successfully.");

	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return students;
	}
	
	
	public int getTotalStudents() {

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try{
	    		con = DBConnection.getConnection();
	    		 ps = con.prepareStatement(GET_TOTAL_STUDENT);
	    		 rs = ps.executeQuery();
	    		 
	    		 if(rs.next()) {
	    			 return rs.getInt(1);
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

	    return 0;
	}
	
	
	public boolean changePassword(int userId, String newPassword) {

	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	    	con = DBConnection.getConnection();
	    	ps = con.prepareStatement(CHANGE_PASSWORD);
	    	
	    	ps.setString(1, newPassword);
	    	ps.setInt(2, userId);

	    	int x = ps.executeUpdate();
	    	if(x > 0) {
	    	    return true;
	    	}

	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    } 
	    finally{
	        try{
	            if(ps != null)
	                ps.close();
	            if (con != null)
	                con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return false;
	}

}
