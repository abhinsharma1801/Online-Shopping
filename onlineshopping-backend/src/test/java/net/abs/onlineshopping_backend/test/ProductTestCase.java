package net.abs.onlineshopping_backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.abs.onlineshopping_backend.dao.ProductDAO;
import net.abs.onlineshopping_backend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDao;
	private static Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.abs.onlineshopping_backend");
		context.refresh();
		productDao = (ProductDAO)context.getBean("productDAO");
	}
	
	@Test
	public void testCRUDProduct() {
		
		product = new Product();
		product.setName("Oppo Selfie S53");
		product.setBrand("Oppo");
		product.setDescription("This is some description for oppo mobile phones!");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		assertEquals("Something went wrong while inserting a new product!",
				true, productDao.add(product));	
		
		product = productDao.get(2);
		product.setName("Samsung Galaxy S7");
		assertEquals("Something went wrong while updating an existing product!",
				true, productDao.update(product));	
		
		assertEquals("Something went wrong while deleting an existing product!",
				true, productDao.delete(product));	
		
		assertEquals("Something went wrong while fetching all products!",
				6, productDao.list().size());		
		
	}
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching active products!",
				5, productDao.listActiveProduct().size());	
	}
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching active products by Category!",
				3, productDao.listActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching active products by Category!",
				2, productDao.listActiveProductsByCategory(1).size());
	}
	
	@Test
	public void testGetLatestActiveProduct() {
		assertEquals("Something went wrong while fetching latest active products!",
				3, productDao.getLatestActiveProducts(3).size());
	}
}




