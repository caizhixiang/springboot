package com.caizhixiang.service;

import com.caizhixiang.model.User;

public interface UserService {
	public User findById(int id);
	
	public User findCondition(User user);
}
