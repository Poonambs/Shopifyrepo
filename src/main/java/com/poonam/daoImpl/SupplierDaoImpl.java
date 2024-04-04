package com.poonam.daoImpl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poonam.dao.SupplierDao;
import com.poonam.entity.Product;
import com.poonam.entity.Supplier;
import com.poonam.exception.ResourceAlreadyExistException;
import com.poonam.exception.ResourceNotExistsException;
import com.poonam.exception.SomethingWentWrongException;
import com.poonam.model.SupplierModel;




@Component
public class SupplierDaoImpl implements SupplierDao {

	@Autowired
	private SessionFactory sf;

	private String ExceptionError = "Something Went Wrong During operation";

	private String resourceExistError = " Already Exists check unique Properties id,name,mobile";


	
	@Override
	public int addSupplier(Supplier supplier) {
		Session session = sf.openSession();
		
		try {

			
			Supplier existingSupplier = session.createQuery("FROM Supplier WHERE supplierId = :supplierId OR supplierName= :supplierName OR mobileNumber = :mobileNumber " ,Supplier.class)
		            .setParameter("supplierId", supplier.getSupplierId())
		            .setParameter("supplierName", supplier.getSupplierName())
		            .setParameter("mobileNumber", supplier.getMobileNumber())
		            .uniqueResult();
			if (existingSupplier == null) {
				session.save(supplier); 
				session.beginTransaction().commit(); 
				return 1;
			} else {
				if (existingSupplier.getSupplierId()==(supplier.getSupplierId())) {
	                throw new ResourceAlreadyExistException("Supplier with ID '" + supplier.getSupplierId() + "' already exists.");
				}else if (existingSupplier.getSupplierName().equals(supplier.getSupplierName())) {
	            throw new ResourceAlreadyExistException("Supplier with Name '" + supplier.getSupplierName() + "' already exists.");}
	             else if(existingSupplier.getMobileNumber().equals(supplier.getMobileNumber())) {
	                throw new ResourceAlreadyExistException("Supplier with Mobile Number '" + supplier.getMobileNumber() + "' already exists.");
	            }
			}
		
		} catch (ResourceAlreadyExistException e) {
			throw e;
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return 1;

	}
	
		
	@Override
	public Supplier getSupplierById(long supplierId) {
		Session session = sf.openSession();
		
		
		Supplier s = session.get(Supplier.class, supplierId);
		return s;
		
	}

	@Override
	public List<Supplier> getAllSuppliers() {
	  Session session = sf.openSession();
	     List<Supplier> list = null;
	     try {
	        Criteria criteria = session.createCriteria(Supplier.class);
	        list = criteria.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return list;
	}

	@Override
	public String deleteSupplier(long supplierId) {
		Session session = sf.openSession();
		Supplier s = session.get(Supplier.class, supplierId);
		if(s!=null)
		{
			
			session.delete(s);
		}
		else
		{
			throw new ResourceNotExistsException("Supplier not exist:"+ supplierId);
		}
		
		return "Deleted Sucessfully"; 
		
	
		

	}

	@Override
	public int updateSupplier(Supplier Updatedsupplier) {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		try  {

			 Supplier existingSupplier = session.get(Supplier.class, Updatedsupplier.getSupplierId());

			if (existingSupplier != null) {
				session.evict(existingSupplier);
				session.update(Updatedsupplier);
				t.commit();
				return 1;
			} else {

				throw new ResourceNotExistsException("Supplier with ID " + Updatedsupplier.getSupplierId() + " not found.");
			}

		} catch (ResourceNotExistsException e) {
			throw e;
		} catch (Exception e) {

			throw new SomethingWentWrongException("Something went wrong while updating the supplier.");
		}
		
	}


	@Override
	public List<Supplier> suppliersOrderByDesc() {
		Session session = sf.openSession();
	     List<Supplier> list = null;
	     try {
	        Criteria criteria = session.createCriteria(Supplier.class);
	        criteria.addOrder(Order.desc("city"));
	        list = criteria.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return list;
	}


	@Override
	public List<Supplier> suppliersFilterByCityName() {
		Session session = sf.openSession();
	    List<Supplier> list = null;
	     try {
	        Criteria criteria = session.createCriteria(Supplier.class);
	        criteria.add(Restrictions.eq("city", "pune"));
	        list = criteria.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	
	        session.close();
	    }

	    return list;
	}


	@Override
	public List<Supplier> suppliersFilterByNameLike() {
		Session session = sf.openSession();
	    List<Supplier> list = null;
	     try {
	        Criteria criteria = session.createCriteria(Supplier.class);
	        criteria.add(Restrictions.like("supplierName", "Poonam"));
	        list = criteria.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	
	        session.close();
	    }

	    return list;
	}
	
}
		





