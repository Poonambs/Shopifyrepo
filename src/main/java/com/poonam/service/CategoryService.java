package com.poonam.service;

import java.util.List;

import com.poonam.entity.Category;
import com.poonam.model.CategoryModel;


public interface CategoryService {
	
	public int addCategory(CategoryModel categoryModel);
	public CategoryModel getCategoryById(long categoryId);

	public List<CategoryModel> getAllCategory();
	
	public String deleteCatgeory(long categoryId);
	
	public int updateCategory(CategoryModel categoryModel);
	public List<CategoryModel> categoryOrderByDesc();
	public List<CategoryModel> categoryFilterByName();
	public List<CategoryModel> categoryFilterByNameLike();
	List<CategoryModel> categoryFilterByDiscountGreaterThan(double minValue);
	
	

}
