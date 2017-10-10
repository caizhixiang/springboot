package com.caizhixiang.biz.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.caizhixiang.biz.UserBiz;
import com.caizhixiang.mapper.UserMapper;
import com.caizhixiang.model.Function;
import com.caizhixiang.model.User;

@Service("userBiz")
public class UserBizImpl implements UserBiz {
	@Resource
	private UserMapper userMapper;
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userMapper.findById(id);
	}
	@Override
	public User findCondition(User user) {
		// TODO Auto-generated method stub
		List<User> list = userMapper.findCondition(user);
		return CollectionUtils.isEmpty(list)?null:list.get(0);
	}
	@Override
	public Set<String> findFunctionsByUserName(String name) {
		// TODO Auto-generated method stub
		Set<String> result=new HashSet<String>();
		List<User> list = userMapper.findFunctionsByUserName(name);
		if (CollectionUtils.isNotEmpty(list)) {
			list.stream().filter(n->n!=null).forEach(user->{
				List<Function> funList=user.getFunctionList();
				if(CollectionUtils.isNotEmpty(funList)){
					funList.stream().filter(n->n!=null).forEach(function->{
						result.add(function.getContent());
					});
				}
			});
		}
		return result;
	}

}
