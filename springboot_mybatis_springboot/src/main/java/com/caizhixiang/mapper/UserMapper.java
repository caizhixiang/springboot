package com.caizhixiang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.caizhixiang.model.User;

@Repository
public interface UserMapper {
	public User findById(@Param("id")int id);

	public List<User> findCondition(User user);
	
	public List<User> findFunctionsByUserName(@Param("name")String name);
}
