package com.poonam.daoImpl;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poonam.dao.ProductDao;
import com.poonam.entity.Category;
import com.poonam.entity.Product;
import com.poonam.entity.Supplier;
import com.poonam.exception.NonNullableAssociationException;
import com.poonam.exception.ResourceAlreadyExistException;
import com.poonam.exception.ResourceNotExistsException;
import com.poonam.exception.SomethingWentWrongException;
import com.poonam.model.SupplierModel;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;



@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sf;

	private String ExceptionError = "Something Went Wrong During operation";

	public ProductDaoImpl() {

	}

	@Override
	public int addProduct(Product product) {
	    Session session = sf.openSession();
	    

	    try {
	        
	        Query<Product> query = session.createQuery("FROM Product WHERE productName = :productName", Product.class);
	        query.setParameter("productName", product.getProductName());
	        Product db = query.uniqueResult();

	        if (db != null) {
	        	throw new ResourceAlreadyExistException("Product with name " + product.getProductName() + " already exists.");
	            
	        }

	      Transaction  transaction = session.beginTransaction();
	        session.save(product);
	        transaction.commit(); // Commit the transaction after saving
	        return 1;
	    } 
	    catch (TransientPropertyValueException e) {
	        throw new NonNullableAssociationException("Non-nullable association not properly set: " + e.getMessage());
	    }catch (Exception e) {
	      
	        
	        e.printStackTrace();
	        throw new SomethingWentWrongException("Error occurred while adding product");
	    } 
	
		
	    }
	


    
	    
	@Override
	public Product getProductById(long productId) {
		
		Session s=sf.openSession();
		Product p=s.get(Product.class, productId );
		return p;

	}

	@Override
	public List<Product> getAllProducts() {
		Session session = sf.openSession();
	     List<Product> list = null;
	     try {
	        Criteria criteria = session.createCriteria(Product.class);
	        list = criteria.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return list;
	}
	

	@Override
	public String deleteProduct(long productId) {
		Session session = sf.openSession();
		Product p = session.get(Product.class, productId);
		if(p!=null)
		{
			
			session.delete(p);
		}
		else
		{
			throw new ResourceNotExistsException("Supplier not exist:"+ productId);
		}
		
		return "Deleted Sucessfully"; 
		
	
		

	}

		

	@Override
	public int updateProduct(Product updatedProduct) {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		try  {

			 Product existingProduct = session.get(Product.class, updatedProduct.getProductId());

			if (existingProduct != null) {
				session.evict(existingProduct);
				session.update(updatedProduct);
				t.commit();
				return 1;
			} else {

				throw new ResourceNotExistsException("Supplier with ID " + updatedProduct.getProductId() + " not found.");
			}

		} catch (ResourceNotExistsException e) {
			throw e;
		} catch (Exception e) {

			throw new SomethingWentWrongException("Something went wrong while updating the supplier.");
		}
		
	}

		

    @Override
	    public double getMaxPrice() {
 	   Session session  = sf.openSession();
	       double maxPrice=0;
	        try {
	           
	            Criteria criteria = session.createCriteria(Product.class);
	           List list =criteria.setProjection(Projections.max("productPrice")).list();
	           if(!list.isEmpty())
	           {
	        	   
	        	   
	            maxPrice=(double) list.get(0);
	            
	           } }  
	           catch(Exception e)
	           {e.printStackTrace();
	        	  throw  new SomethingWentWrongException(ExceptionError);
	           }
			return maxPrice;
	           
	}

   
	@Override
    public List<String> getMaxPriceProduct() {
 	   
 	  
 	       List<String> maxProductNames = new ArrayList<>();
 	       try {
 	           Session session = sf.openSession();
 	           Criteria criteria = session.createCriteria(Product.class);
 	           List<Double> maxPrices = criteria.setProjection(Projections.max("productPrice")).list();
 	           if (!maxPrices.isEmpty()) {
 	               Double maxPrice = maxPrices.get(0);
 	              
 	               List<Product> maxProducts = session.createCriteria(Product.class)
 	                                                   .add(Restrictions.eq("productPrice", maxPrice))
 	                                                   .list();
 	               for (Product product : maxProducts) {
 	                   maxProductNames.add(product.getProductName());
 	               }
 	           }
 	       } catch (Exception e) {
 	           e.printStackTrace();
 	           throw new SomethingWentWrongException(ExceptionError);
 	       }
 	       return maxProductNames;
 	   }
	
	@Override
	public Product getProductByName(String productName) {
		Session session = sf.openSession();
		List<Product> list = null;
		Product productEntity = null;
		try {

			Criteria criteria = session.createCriteria(Product.class);

			SimpleExpression eq = Restrictions.eq("productName", productName);

			list = criteria.add(Restrictions.eq("productName", productName)).list();
			if (!list.isEmpty()) {
				productEntity = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return productEntity;

	}


	@Override
	public List<Product> getProductByPriceRange(double minPrice, double maxPrice) {
		return null;

	}

	@Override
	public List<Product> getAllProductStartWith(String expression) {
		return null;

	}

	@Override
	public List<Product> sortProducts(String orderType, String field) {
		Session session = sf.openSession();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
		return null;

	}
	@Override
	public SupplierModel getSupplierById(long supplierId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	  

}
