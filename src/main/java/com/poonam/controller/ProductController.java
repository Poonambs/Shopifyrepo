package com.poonam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.poonam.entity.Product;
import com.poonam.exception.ResourceNotExistsException;
import com.poonam.exception.SomethingWentWrongException;
import com.poonam.model.ProductModel;
import com.poonam.service.ProductService;

import net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Factory.AccessResolver;



@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService service;


	@PostMapping("add-product")
	public String addProduct(@RequestBody  ProductModel productModel) {
           service.addProduct(productModel);
		
		return "Added Successfully";
	}

	@GetMapping("/get-product-by-id/{productId}") // {_} placeholder
	public ResponseEntity<ProductModel> getProductById(@PathVariable long productId) {

		ProductModel product = service.getProductById(productId);
		return new ResponseEntity<ProductModel>(product,HttpStatus.OK);
	}
	
	@GetMapping("/get-all-products")
	public List<ProductModel> getAllProducts(ProductModel productModel){
		
		return service.getAllProducts();	
	}
	
	
	@DeleteMapping("/delete-product-by-id")
	public ResponseEntity<String> deleteProductById(@PathVariable long productId){
		
		 
		
		return ResponseEntity.ok(service.deleteProduct(productId));

	
		
	}
	@GetMapping("/byName/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable String productName) {
		Product product = service.getProductByName(productName);
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("/update-product")
	public String UpdateProduct(@RequestBody  ProductModel productModel){
		
	 service.updateProduct(productModel);
	return "Updated";
		
	
	}
	
	@GetMapping("/get-maxPrice") // {_} placeholder
	public double getProductMaxProduct() {

		return service.getMaxPrice();	
	}
	
	@GetMapping("/get-maxPriceProduct") // {_} placeholder
	public List<String> getMaxProduct() {

		return service.getMaxProduct();
	}
	
	@GetMapping("/sort")
	public ResponseEntity<List<Product>> sortProducts(@RequestParam String orderType,
			@RequestParam String field) {
		List<Product> products = service.sortProducts(orderType, field);
		return ResponseEntity.ok(products);
	}
  @PostMapping("/upload-sheet")
	public String uploadSheet(@RequestParam MultipartFile file) {
	   
	String msg= service.uploadSheet(file);
	return msg;

}
  
	

   
}
