package com.library.service;

import java.util.List;

import com.library.dao.CategoryDAO;
import com.library.dto.Category;

public class CategoryService {
	private CategoryDAO categoryDAO = new CategoryDAO();
	

	public boolean addCategory(Category category){

		// Category Name Validation
		if(category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
			return false;
		}

		// Duplicate Category Validation
		List<Category> categories = categoryDAO.getAllCategories();

		for(Category c : categories) {
			if(c.getCategoryName().equalsIgnoreCase(category.getCategoryName())) {
				return false;
			}
		}

		return categoryDAO.addCategory(category);
	}
	
	
	

	public List<Category> getAllCategories(){
		return categoryDAO.getAllCategories();
	}

}
