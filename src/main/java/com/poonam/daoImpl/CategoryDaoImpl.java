package com.poonam.daoImpl;

import java.util.List;

import javax.persistence.RollbackException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poonam.dao.CategoryDao;
import com.poonam.entity.Category;
import com.poonam.entity.Supplier;
import com.poonam.exception.ResourceAlreadyExistException;
import com.poonam.exception.ResourceNotExistsException;
import com.poonam.exception.SomethingWentWrongException;
@Component
public class CategoryDaoImpl implements CategoryDao {
	@Autowired
	private SessionFactory sf;

	@Override
	public int addCategory(Category category) {
		Session session = sf.openSession();
		try {

			Category categoryentity = session.get(Category.class, category.getCategoryId());

			if (categoryentity == null) {
				session.save(category); //


				session.beginTransaction().commit(); 
				return 1;
			} else {
				throw new ResourceAlreadyExistException("Category alredy exist");
			}

		} catch (ResourceAlreadyExistException e) {
			throw new ResourceAlreadyExistException("Category alredy exist");
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("something went wrong");
		}

	}




	@Override
	public Category getCategoryById(long categoryId) {
		Session s=sf.openSession();
		Category c= s.get(Category.class, categoryId);

		return c;
	}

	@Override
	public List<Category> getAllCategorys() {
		
		 Session session = sf.openSession();
		     List<Category> list = null;
		     try {
		        Criteria criteria = session.createCriteria(Category.class);
		        list = criteria.list();
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        session.close();
		    }

		    return list;
		}
		
	

	@Override
	public String deleteCategory(long categoryId) {

		Session session=sf.openSession();
		session.beginTransaction().commit(); 
		Category c= session.get(Category.class, categoryId);
		if(c!=null)
		{
			session.delete(c);
			
		}
		else {

			throw new ResourceNotExistsException("Id Not Found");
		}

		return "Delete Successfully";
	}

	@Override
	public int updateCategory(Category updatedCategory) {
		Session session=sf.openSession();
		Transaction t = session.beginTransaction();
				try
		{
			Category ExistingCateory= session.get(Category.class,updatedCategory.getCategoryId());
			if(ExistingCateory!=null) {
				session.evict(ExistingCateory);
				session.update(updatedCategory);
	            	t.commit(); 
				return 1;

			}
			else
			{
				throw new ResourceNotExistsException("Resource not exist");

			}
		}
		catch(ResourceNotExistsException e){
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while update");
		}
	}





	@Override
	public List<Category> categoryOrderByDesc() {
		Session session = sf.openSession();
	     List<Category> list = null;
	     try {
	        Criteria criteria = session.createCriteria(Category.class);
	        criteria.addOrder(Order.desc("categoryName"));
	        list = criteria.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return list;
	}
	

	@Override
	public List<Category> categoryFilterByName() {
		Session session = sf.openSession();
	     List<Category> list = null;
	     try {
	        Criteria criteria = session.createCriteria(Category.class);
	        criteria.add(Restrictions.eq("categoryName", "Stationary"));
	        list = criteria.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return list;
	}
	@Override
	public List<Category> categoryFilterByNameLike() {
		Session session = sf.openSession();
	     List<Category> list = null;
	     try {
	        Criteria criteria = session.createCriteria(Category.class);
	        criteria.add(Restrictions.like("description", " boxes "));
	        list = criteria.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return list;
	}

	
	@Override
	public List<Category> categoryFilterByDiscountGreaterThan(double minValue) {
	    Session session = sf.openSession();
	    List<Category> list = null;
	    try {
	        Criteria criteria = session.createCriteria(Category.class);
	        criteria.add(Restrictions.gt("discount", minValue));
	        list = criteria.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return list;
	}

}
