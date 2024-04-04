package com.poonam.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poonam.dao.CategoryDao;
import com.poonam.dao.SupplierDao;
import com.poonam.entity.Category;
import com.poonam.entity.Supplier;
import com.poonam.exception.ResourceNotExistsException;
import com.poonam.model.CategoryModel;
import com.poonam.model.SupplierModel;
import com.poonam.service.CategoryService;
@Component
public class CategoryServiceImpl  implements CategoryService{

	@Autowired
	private ModelMapper mm;

	@Autowired
	private CategoryDao dao;

	@Override
	public int addCategory(CategoryModel categoryModel) {

		Category Mapping=mm.map(categoryModel, Category.class);

		return dao.addCategory(Mapping);

	}

	@Override
	public CategoryModel getCategoryById( long categoryId) {

		Category category= dao.getCategoryById(categoryId);
		if(category!=null)
		{
			CategoryModel categoryM= mm.map(category, CategoryModel.class);
			return categoryM;

		}
		else
		{

			throw new ResourceNotExistsException("Category not exist:"+ categoryId);

		}
	}
	@Override
	public String deleteCatgeory( long categoryId) {

		String category=dao.deleteCategory(categoryId);
		return "deleted sucessfully";
	}


	@Override
	public List<CategoryModel> getAllCategory() {
		List<Category>categorylist=dao.getAllCategorys();
		List<CategoryModel>list= new ArrayList<>();
		if(!categorylist.isEmpty())
		{
			for (Category category : categorylist) {
				CategoryModel categoryModel=mm.map(category, CategoryModel.class);
				list.add(categoryModel);

			}}
		else {
			throw new ResourceNotExistsException("category not exists");
		}
		return list;
	}
	@Override
	public int updateCategory(CategoryModel categoryModel) {
		Category Mapping=mm.map(categoryModel, Category.class);

		return dao.updateCategory(Mapping);

	}

	@Override
	public List<CategoryModel> categoryOrderByDesc() {

		List<Category>categorylist=dao.categoryOrderByDesc();
		List<CategoryModel>list= new ArrayList<>();
		if(!categorylist.isEmpty())
		{
			for (Category category : categorylist) {
				CategoryModel categoryModel=mm.map(category, CategoryModel.class);
				list.add(categoryModel);

			}}
		else {
			throw new ResourceNotExistsException("category not exists");
		}
		return list;
	}



	@Override
	public List<CategoryModel> categoryFilterByName() {
		List<Category>categorylist=dao.categoryFilterByName();
		List<CategoryModel>list= new ArrayList<>();
		if(!categorylist.isEmpty())
		{
			for (Category category : categorylist) {
				CategoryModel categoryModel=mm.map(category, CategoryModel.class);
				list.add(categoryModel);

			}}
		else {
			throw new ResourceNotExistsException("category not exists");
		}
		return list;
	}

     @Override
	public List<CategoryModel> categoryFilterByNameLike() {
		List<Category>categorylist=dao.categoryFilterByNameLike();
		List<CategoryModel>list= new ArrayList<>();
		if(!categorylist.isEmpty())
		{
			for (Category category : categorylist) {
				CategoryModel categoryModel=mm.map(category, CategoryModel.class);
				list.add(categoryModel);

			}}
		else {
			throw new ResourceNotExistsException("category not exists");
		}
		return list;
	}

	@Override
	public List<CategoryModel> categoryFilterByDiscountGreaterThan(double minValue) {
		List<Category>categorylist=dao.categoryFilterByDiscountGreaterThan(minValue);
		List<CategoryModel>list= new ArrayList<>();
		if(!categorylist.isEmpty())
		{
			for (Category category : categorylist) {
				if (category.getDiscount() > minValue) {
		            
				CategoryModel categoryModel=mm.map(category, CategoryModel.class);
				list.add(categoryModel);
				}
			}}
		else {
			throw new ResourceNotExistsException("category not exists");
		}
		return list;
	}


    


}









