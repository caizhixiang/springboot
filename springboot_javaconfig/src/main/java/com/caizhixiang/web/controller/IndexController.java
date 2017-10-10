package com.caizhixiang.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/test")
public class IndexController {
	
	@RequestMapping("/index")
	public ModelAndView toIndex(){
		
		return new ModelAndView("index");
	}
	
	
}
