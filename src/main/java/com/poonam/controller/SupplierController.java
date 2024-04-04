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

import com.poonam.entity.Supplier;
import com.poonam.exception.ResourceNotExistsException;
import com.poonam.model.SupplierModel;
import com.poonam.service.SupplierService;




@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	SupplierService service;

	@PostMapping("/add-supplier")
	public String addSupplier(@RequestBody  @Valid SupplierModel supplierModel) throws MethodArgumentNotValidException{

		service.addSupplier(supplierModel);

			return "Added Successfully";
		
	}

	@GetMapping("/get-supplier-by-id/{supplierId}") 
	public ResponseEntity<SupplierModel> getSupplierById(@PathVariable long supplierId) {

		return ResponseEntity.ok(service.getSupplierById(supplierId));
		
	}
	@GetMapping("/get-all-supplier") 
	public List<SupplierModel> getSuppliers(SupplierModel supplierModel) {

	return service.getAllSuppliers();
		
	}
	
	@DeleteMapping("/delete-Supplier-by-Id/{supplierId}") 
	public ResponseEntity<String> deleteSupplierById(@PathVariable long supplierId) {

		return ResponseEntity.ok(service.deleteSupplier(supplierId));
		
	}
	
	
	@PutMapping("/update-supplier")
	public String UpdateSupplier(@RequestBody SupplierModel supplierModel)  {

		service.updateSupplier(supplierModel);

		return "Update Successfully";
		
		
	}
	@GetMapping("/get-all-supplier-by-desc") 
	public List<SupplierModel> getSuppliersDesc(SupplierModel supplierModel) {

	return service.suppliersOrderByDesc();
		
	}
	@GetMapping("/suppliersFilterByCityName") 
	public List<SupplierModel> suppliersFilterByCityName(SupplierModel supplierModel) {

	return service.suppliersFilterByCityName();
		
	}
	@GetMapping("/suppliersFilterByNameLike") 
	public List<SupplierModel> suppliersFilterByNameLike(SupplierModel supplierModel) {

	return service.suppliersFilterByNameLike();
		
	}
	

}
