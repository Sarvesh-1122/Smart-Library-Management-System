package com.library.service;

import java.util.List;
import com.library.dao.UserDAO;
import com.library.dto.User;

public class UserService {
	private UserDAO userDAO = new UserDAO();
	
	
	

	public boolean registerUser(User user) {

		if (user == null) {
			return false;
		}

		if (user.getName() == null || user.getName().trim().isEmpty()) {
			return false;
		}

		if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
			return false;
		}

		if (user.getMobile() == null || user.getMobile().trim().isEmpty()) {
			return false;
		}

		if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			return false;
		}

		if (user.getRole() == null || user.getRole().trim().isEmpty()) {
			return false;
		}

		return userDAO.registerUser(user);
	}
	
	
	
	public User loginUser(String email, String password) {
		if (email == null || email.trim().isEmpty()) {
			return null;
		}
		if( password == null || password.trim().isEmpty()) {
			return null;
		}
		
		return userDAO.loginUser(email, password);
	}
	
	
	public List<User> getAllStudents(){
		return userDAO.getAllStudents();
	}
	
	
	public int getTotalStudents() {
	    return userDAO.getTotalStudents();
	}
	
	
	public boolean changePassword(int userId, String newPassword) {
	    return userDAO.changePassword(userId, newPassword);
	}
	
	
	
	
}
