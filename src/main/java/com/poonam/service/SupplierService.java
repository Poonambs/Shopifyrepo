package com.poonam.service;

import java.util.List;

import com.poonam.entity.Supplier;
import com.poonam.model.SupplierModel;

public interface SupplierService {
	
	public int addSupplier(SupplierModel supplierModel);
	public SupplierModel getSupplierById(long supplierId);

	public List<SupplierModel> getAllSuppliers();
	
	public String deleteSupplier(long supplierId);
	
	public int updateSupplier(SupplierModel supplierModel);
	public List<SupplierModel> suppliersOrderByDesc();
	public List<SupplierModel> suppliersFilterByCityName();
	public List<SupplierModel> suppliersFilterByNameLike();
	
	
	
	
	
	
	
}
