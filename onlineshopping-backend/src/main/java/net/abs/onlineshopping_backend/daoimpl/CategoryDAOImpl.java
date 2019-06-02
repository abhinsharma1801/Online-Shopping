package net.abs.onlineshopping_backend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.abs.onlineshopping_backend.dao.CategoryDAO;
import net.abs.onlineshopping_backend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	/*private static List<Category> categories = new ArrayList<>();
	static {
		
		Category category = new Category();
		category.setId(1);
		category.setName("TV");
		category.setDescription("Television");
		category.setImageURL("tv.png");
		
		categories.add(category);
		
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Mobile");
		category.setImageURL("Mobile.png");
		
		categories.add(category);
		
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("Laptop");
		category.setImageURL("Laptop.png");
		
		categories.add(category);
	}*/
	
	@Override
	public List<Category> list() {
		
		String selectActiveCategory = "from Category where active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}
	
	// Getting single category based on ID
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}
	

	@Override
	public boolean add(Category category) {
		
		try {
			// Add the category to the database table.
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Category category) {
		
		try {
			// Update the category in the database table.
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		
		category.setActive(false);
		try {
			// Update the category in the database table.
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
