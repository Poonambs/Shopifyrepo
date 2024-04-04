package com.poonam.dao;

import java.util.List;


import com.poonam.entity.Supplier;


public interface SupplierDao {

	public int addSupplier(Supplier supplier);
	public Supplier getSupplierById(long supplierId);
	public List<Supplier> getAllSuppliers();
	public String deleteSupplier(long supplierId);
	public int updateSupplier(Supplier supplier);
	public List<Supplier> suppliersOrderByDesc();
	public List<Supplier> suppliersFilterByCityName();
	public List<Supplier> suppliersFilterByNameLike();
	
	


}
