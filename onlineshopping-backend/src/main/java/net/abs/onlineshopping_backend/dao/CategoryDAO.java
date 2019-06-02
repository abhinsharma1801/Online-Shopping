package net.abs.onlineshopping_backend.dao;

import java.util.List;

import net.abs.onlineshopping_backend.dto.Category;

public interface CategoryDAO {

	Category get(int id);
	List<Category> list();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}
