package com.caizhixiang.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caizhixiang.model.User;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	@RequestMapping("/index")
	public String findById(){
		return "index";
	}
	
	@RequestMapping("/")
	public String toLogin() {
		return "login";
	}
	@RequestMapping("/login")
	public String login(User user,Model model){
		String name = user.getName();
		String password = user.getPassword();
		UsernamePasswordToken token =new UsernamePasswordToken(name,password);
//		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (Exception e) {
			log.error(""+e);
			model.addAttribute("msg", "您的账号或密码输入错误！");
			return "redirect:toLogin";
		}
		return "index";
	}
	@RequestMapping("/unauthor")
	public String unauthor() {
		return "/unauthor";
	}
}
