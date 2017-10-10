package com.caizhixiang.biz;

import java.util.Set;

import com.caizhixiang.model.User;

public interface UserBiz {
	public User findById(int id);

	public User findCondition(User user);
	
	public Set<String> findFunctionsByUserName(String name);
}
