package net.abs.onlineshoppingfrontend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.abs.onlineshopping_backend.dao.CategoryDAO;
import net.abs.onlineshopping_backend.dao.ProductDAO;
import net.abs.onlineshopping_backend.dto.Category;
import net.abs.onlineshopping_backend.dto.Product;
import net.abs.onlineshoppingfrontend.exception.ProductNotFoundException;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("greeting", "Welcome to Spring MVC Project");
		mav.addObject("title", "Home");
		
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		mav.addObject("userClickHome", true);
		mav.addObject("categories", categoryDAO.list());
		return mav;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "About Us");
		mav.addObject("userClickAbout", true);
		return mav;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "Contact Us");
		mav.addObject("userClickContact", true);
		return mav;
	}

	// Show All Products
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "All Products");
		mav.addObject("categories", categoryDAO.list());
		mav.addObject("userClickAllProducts", true);
		return mav;
	}

	// Show Products based on category
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		
		ModelAndView mav = new ModelAndView("page");
		
		// categoryDao to fetch a single category
		Category category = categoryDAO.get(id);
		mav.addObject("title", category.getName());
		mav.addObject("categories", categoryDAO.list());
		mav.addObject("category", category);
		mav.addObject("userClickCategoryProducts", true);
		return mav;
	}
	
	// Show Single Page Product
	// This is a checked Exception otherwise we would declared
	// it inside try catch block.
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException{
		
		ModelAndView mav = new ModelAndView("page");
		
		Product product = productDAO.get(id);
		
		if(product == null)
			throw new ProductNotFoundException();
		
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		
		mav.addObject("title", product.getName());
		mav.addObject("product", product);
		mav.addObject("userClickShowProduct", true);
		
		return mav;
	}

	
	
	
	// Demo of @RequestParam. Not a part of actual project.
	@RequestMapping(value = "/test")
	public ModelAndView test(@RequestParam(value = "greeting", required = false) String greeting) {
		if (greeting == null)
			greeting = "Hello there !";
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("greeting", greeting);
		return mav;
	}

	// Demo of @PathVariable. Not a part of actual project.
	@RequestMapping(value = "/test/{greeting}")
	public ModelAndView test1(@PathVariable("greeting") String greeting) {
		if (greeting == null)
			greeting = "Hello there !";
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("greeting", greeting);
		return mav;
	}
}
