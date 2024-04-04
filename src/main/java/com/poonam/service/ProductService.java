package com.poonam.service;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.poonam.entity.Product;
import com.poonam.model.ProductModel;


public interface ProductService {
	
	public int addProduct(ProductModel productmodel);
	public ProductModel getProductById(long productId);

	public List<ProductModel> getAllProducts();
	
	public String deleteProduct(long productId);
	
	public int updateProduct(ProductModel productModel);
	
	public double getMaxPrice();
	public List< String> getMaxProduct();
	public String uploadSheet(MultipartFile file);
	public Product getProductByName(String productName);
	List<Product> sortProducts(String orderType, String field);
}
