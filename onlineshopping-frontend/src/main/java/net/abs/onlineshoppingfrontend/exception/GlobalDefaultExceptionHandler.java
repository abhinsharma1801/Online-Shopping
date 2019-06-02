package net.abs.onlineshoppingfrontend.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {
		
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("errorTitle", "The page is not constructed!");
		mav.addObject("errorDescription", "The page you are looking for is not available now!");
		mav.addObject("title", "404 error page");
		return mav;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("errorTitle", "Product not available!");
		mav.addObject("errorDescription", "The product you are looking for is not available right now!");
		mav.addObject("title", "Product unavailable");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception e) {
		
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("errorTitle", "Contact your Administrator!");
		mav.addObject("errorDescription", e.toString());
		mav.addObject("title", "Error");
		return mav;
	}
}
