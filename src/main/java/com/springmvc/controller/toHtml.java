package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/to")
public class toHtml {
	
	@RequestMapping("/page")
	public ModelAndView toPage (){
		return new ModelAndView("test");
	}

}
