package com.fqt.project.service.impl;

import org.springframework.stereotype.Service;

import com.fqt.project.entity.User;
import com.fqt.project.mapper.UserMapper;
import com.fqt.project.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	
	private UserMapper userMapper;

	@Override
	public User Login(User user) {
		// TODO Auto-generated method stub
		return userMapper.Login(user);
	}

}
