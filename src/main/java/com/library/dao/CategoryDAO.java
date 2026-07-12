package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.dto.Category;
import com.library.util.DBConnection;

public class CategoryDAO {
	private static final String INSERT_CATEGORY = "INSERT INTO category(category_name) VALUES(?)";
	private static final String GET_ALL_CATEGORY = "SELECT * FROM category";
	
	
	 public boolean addCategory(Category category) {
	        boolean isAdded = false;

	        Connection con = null;
	        PreparedStatement ps = null;

	        try{
	            con = DBConnection.getConnection();
	            ps = con.prepareStatement(INSERT_CATEGORY);
	            
	            ps.setString(1, category.getCategoryName());
	            
	            int rows = ps.executeUpdate();
	            if (rows > 0) {
	                isAdded = true;
	            }
	        } 
	        catch (Exception e){
	            e.printStackTrace();
	        } 
	        finally{
	            try{
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

	 
	 
	 
	 public List<Category> getAllCategories() {
	        List<Category> categories = new ArrayList<>();

	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try{
	            con = DBConnection.getConnection();
	            ps = con.prepareStatement(GET_ALL_CATEGORY);

	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Category category = new Category();

	                category.setCategoryId(rs.getInt("category_id"));
	                category.setCategoryName(rs.getString("category_name"));

	                categories.add(category);
	            }

	        } 
	        catch (Exception e) {
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
	            catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return categories;

	    }
	 
	 
	 

}
