package com.poonam.dao;

import java.util.List;

import com.poonam.dao.*;
import com.poonam.entity.Product;
import com.poonam.model.SupplierModel;


public interface ProductDao {
	
	public int addProduct(Product product);
	public Product getProductById(long productId);
	public List<Product> getAllProducts();
	public String deleteProduct(long productId);
	public int updateProduct(Product product);
	
	public Product getProductByName(String productName);
	public List<Product> getProductByPriceRange(double minPrice, double maxPrice);
	public List<Product> getAllProductStartWith(String expression);
	public List<Product> sortProducts(String orderType, String field);
	public double getMaxPrice();
	public List<String> getMaxPriceProduct();
	public SupplierModel getSupplierById(long supplierId);
}
