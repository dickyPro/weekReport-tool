package com.gtmap.platform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

public class BaseController<T> {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected static final String ERROR = "error"; 
	protected ModelAndView modelAndView = new ModelAndView();
	
	/**
	 * 设置Request和Response
	 * @param arg0
	 * @param arg1
	 */
	@ModelAttribute
	public void setRequestAndResponse(HttpServletRequest arg0, HttpServletResponse arg1) {
		this.request = arg0;
		this.response = arg1;
		this.session = request.getSession();
	}
}
