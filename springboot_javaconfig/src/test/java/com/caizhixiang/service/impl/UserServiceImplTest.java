package com.caizhixiang.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.caizhixiang.model.User;
import com.caizhixiang.service.UserService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void testFindById() {
		System.out.println(userService.findById(1));
	}

	@Test
	public void testFindCondition() {
		User user=new User();
		user.setName("admin");
		user.setPassword("123456");
		System.out.println(userService.findCondition(user));
	}

}
