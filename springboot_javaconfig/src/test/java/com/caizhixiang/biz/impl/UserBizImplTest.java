package com.caizhixiang.biz.impl;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.caizhixiang.biz.UserBiz;
import com.caizhixiang.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBizImplTest {
	@Autowired
	private UserBiz userBiz;
	@Test
	public void testFindCondition() {
		User user =new User();
		user.setName("admin");
		user.setPassword("123456");
		User findById = userBiz.findById(1);
//		System.out.println(findById);
		User user2 = userBiz.findCondition(user);
//		System.out.println(user2);
	}
	@Test
	public void testFindFunctionsByUserName(){
		Set<String> set = userBiz.findFunctionsByUserName("admin");
		System.out.println(set);
	}

}
