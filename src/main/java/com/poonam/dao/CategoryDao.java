package com.poonam.dao;

import java.util.List;

import com.poonam.entity.Category;


public interface CategoryDao {
	public int addCategory(Category category);
	public Category getCategoryById(long categoryId);
	public List<Category> getAllCategorys();
	public String deleteCategory(long categoryId);
	public int updateCategory(Category category);
	public List<Category> categoryOrderByDesc();
	public List<Category> categoryFilterByName();
	public List<Category> categoryFilterByNameLike();
	List<Category> categoryFilterByDiscountGreaterThan(double minValue);

}
