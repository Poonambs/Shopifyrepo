package com.poonam.serviceImpl;

import java.security.Provider.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.poonam.dao.SupplierDao;
import com.poonam.entity.Supplier;
import com.poonam.exception.ResourceNotExistsException;
import com.poonam.model.SupplierModel;
import com.poonam.service.SupplierService;



@Component
public class SupplierServiceImpl<Stirng> implements SupplierService {
	@Autowired
	private ModelMapper mm;

	@Autowired
	private SupplierDao dao;
	
	
	public int addSupplier(SupplierModel supplierModel) {
		
		Supplier Mapping=mm.map(supplierModel, Supplier.class);
		
				 return dao.addSupplier(Mapping);

	}

	@Override
	public SupplierModel getSupplierById(long supplierId) {
		Supplier supllier=dao.getSupplierById(supplierId);
	
		
		if(supllier!=null)
		{
			SupplierModel supplier= mm.map(supllier, SupplierModel.class);
			return supplier;
		}
		else
		{
			throw new ResourceNotExistsException("Supplier not exist:"+ supplierId);
		}
	
		
	}

	@Override
	public List<SupplierModel> getAllSuppliers() {
List<Supplier>supplierlist=dao.getAllSuppliers();
List<SupplierModel>list= new ArrayList<>();
if(!supplierlist.isEmpty())
{
	for (Supplier supplier : supplierlist) {
		SupplierModel supplierModel=mm.map(supplier, SupplierModel.class);
		list.add(supplierModel);
		
	}}
	else {
		throw new ResourceNotExistsException("supplier not exists");
	}
	return list;
}

	@Override
	public String deleteSupplier(long supplierId) {

		String supllier=dao.deleteSupplier(supplierId);
		
		return "Deleted";
		
			
		}

	@Override
	public int updateSupplier(SupplierModel supplierModel) {
		Supplier Mapping=mm.map(supplierModel, Supplier.class);
		
		 return dao.updateSupplier(Mapping);

	}

	@Override
	public List<SupplierModel> suppliersOrderByDesc() {
		List<Supplier>supplierlist=dao.suppliersOrderByDesc();
		List<SupplierModel>list= new ArrayList<>();
		if(!supplierlist.isEmpty())
		{
			for (Supplier supplier : supplierlist) {
				SupplierModel supplierModel=mm.map(supplier, SupplierModel.class);
				list.add(supplierModel);
				
			}}
			else {
				throw new ResourceNotExistsException("supplier not exists");
			}
			return list;
		}

	@Override
	public List<SupplierModel> suppliersFilterByCityName() {
		List<Supplier>supplierlist=dao.suppliersFilterByCityName();
		List<SupplierModel>list= new ArrayList<>();
		if(!supplierlist.isEmpty())
		{
			for (Supplier supplier : supplierlist) {
				SupplierModel supplierModel=mm.map(supplier, SupplierModel.class);
				list.add(supplierModel);
				
			}}
			else {
				throw new ResourceNotExistsException("supplier not exists");
			}
			return list;
		}

	@Override
	public List<SupplierModel> suppliersFilterByNameLike() {
		List<Supplier>supplierlist=dao.suppliersFilterByNameLike();
		List<SupplierModel>list= new ArrayList<>();
		if(!supplierlist.isEmpty())
		{
			for (Supplier supplier : supplierlist) {
				SupplierModel supplierModel=mm.map(supplier, SupplierModel.class);
				list.add(supplierModel);
				
			}}
			else {
				throw new ResourceNotExistsException("supplier not exists");
			}
			return list;
		}
}
	

	

	
		
	
		
	
	
		
	
   
