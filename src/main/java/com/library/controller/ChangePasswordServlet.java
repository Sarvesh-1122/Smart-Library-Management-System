package com.library.controller;

import java.io.IOException;

import com.library.dto.User;
import com.library.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet{
	private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String currentPassword = request.getParameter("currentPassword");
    	String newPassword = request.getParameter("newPassword");
    	String confirmPassword = request.getParameter("confirmPassword");
    	
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	
    	int userId = user.getUserId();
    	String dbPassword = user.getPassword();
    	
    	
    	if (!dbPassword.equals(currentPassword)) {
    	    session.setAttribute("error", "Current Password is Incorrect.");
    	    response.sendRedirect("changePassword.jsp");
    	    return;
    	}
    	
    	
    	if(!newPassword.equals(confirmPassword)) {
    	    session.setAttribute("error", "New Password and Confirm Password do not match.");
    	    response.sendRedirect("changePassword.jsp");
    	    return;
    	}
    	
    	
    	boolean isUpdated = userService.changePassword(userId, newPassword);
    	
    	
    	if(isUpdated) {
    	    session.setAttribute("success", "Password Changed Successfully.");
    	    user.setPassword(newPassword);
    	    session.setAttribute("user", user);
    	} 
    	else{
    	    session.setAttribute("error", "Unable to Change Password.");
    	}
    	
    	
    	response.sendRedirect("changePassword.jsp");
    	
    	
    }

}
