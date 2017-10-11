package com.caizhixiang.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caizhixiang.model.User;
import com.caizhixiang.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user")
	@ResponseBody
	public User getUser(){
		
		return userService.findById(1);
	}
	
	@RequestMapping("add")
	public String add(){
		return "add";
	}
	@RequestMapping("remove")
	public String remove(){
		return "remove";
	}
}
