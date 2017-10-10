package com.caizhixiang.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caizhixiang.biz.UserBiz;
import com.caizhixiang.model.User;
import com.caizhixiang.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserBiz userBiz;
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userBiz.findById(id);
	}
	@Override
	public User findCondition(User user) {
		// TODO Auto-generated method stub
		return userBiz.findCondition(user);
	}

}
