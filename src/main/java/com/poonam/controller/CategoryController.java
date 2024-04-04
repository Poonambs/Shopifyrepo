package com.poonam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poonam.model.CategoryModel;

import com.poonam.service.CategoryService;
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService service;

	@PostMapping("/add-category")
	public String addCategory(@RequestBody  @Valid CategoryModel categoryModel) throws MethodArgumentNotValidException{

		service.addCategory(categoryModel);

			return "Added Successfully";
		
	}
	@GetMapping("/getCategoryById/{categoryId}")
	public ResponseEntity<CategoryModel> getCategoryById(@PathVariable long categoryId){
		
		
		return ResponseEntity.ok(service.getCategoryById(categoryId));
		
	}
	@DeleteMapping("/deleteCategoryById/{categoryId}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable long categoryId){
		
		
		return ResponseEntity.ok(service.deleteCatgeory(categoryId));
		
	}
	@GetMapping("/get-all-category") 
	public List<CategoryModel> getCategory(CategoryModel categoryModel) {

	return service.getAllCategory();
		
	}
	
	@PutMapping("/update-category")
	public String UpdateCategory(@RequestBody CategoryModel categoryModel)  {

		service.updateCategory(categoryModel);

		return "Update Successfully";
		
		
	}

	@GetMapping("/get-all-category-by-desc") 
	public List<CategoryModel> getCategoryDesc(CategoryModel categoryModel) {

	return service.categoryOrderByDesc();
		
	}
	@GetMapping("/categoryFilterByCityName") 
	public List<CategoryModel> categoryFilterByName(CategoryModel categoryModel) {

	return service.categoryFilterByName();
		
	}
	@GetMapping("/categoryFilterByNameLike") 
	public List<CategoryModel> categoryFilterByNameLike(CategoryModel categoryModel) {

	return service.categoryFilterByNameLike();
		
	}
	@GetMapping("/categoryFilterByDiscountGreaterThan/{minValue}") 
	public List<CategoryModel> categoryFilterByDiscountGreaterThan(@PathVariable double minValue) {

	return service.categoryFilterByDiscountGreaterThan(minValue);
		
	}


}
