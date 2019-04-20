package net.abs.onlineshoppingfrontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.abs.onlineshopping_backend.dao.CategoryDAO;
import net.abs.onlineshopping_backend.dto.Category;

@Controller
public class PageController {
	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("greeting", "Welcome to Spring MVC Project");
		mav.addObject("title", "Home");
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
