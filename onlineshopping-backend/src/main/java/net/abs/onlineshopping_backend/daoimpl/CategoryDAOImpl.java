package net.abs.onlineshopping_backend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.abs.onlineshopping_backend.dao.CategoryDAO;
import net.abs.onlineshopping_backend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO{

	private static List<Category> categories = new ArrayList<>();
	
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
	}
	@Override
	public List<Category> list() {
		return categories;
	}
	
	@Override
	public Category get(int id) {
		for(Category c : categories)
		{
			if(c.getId() == id)
				return c;
		}
		return null;
	}

}
