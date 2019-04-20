package net.abs.onlineshopping_backend.dao;

import java.util.List;

import net.abs.onlineshopping_backend.dto.Category;

public interface CategoryDAO {

	List<Category> list();

	Category get(int id);
}
