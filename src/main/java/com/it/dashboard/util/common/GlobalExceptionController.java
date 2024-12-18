package com.it.dashboard.util.common;

import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(HttpSessionRequiredException.class)
	public ModelAndView handleSessionTimeoutException(HttpSessionRequiredException ex) {

		ModelAndView model = new ModelAndView("admin/index");
		
		return model;

	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {

		ModelAndView model = new ModelAndView("admin/index");
		model.addObject("errMsg", "this is null Exception.class");
		
		return model;

	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("admin/index");
		model.addObject("errMsg", "this is Exception.class");
		
		return model;

	}
	
}