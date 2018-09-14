package com.fqt.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fqt.project.entity.User;
import com.fqt.project.mapper.UserMapper;
import com.fqt.project.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUserByName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.findUserByName(userName);
	}

	@Override
	public void modifyUserPassword(Integer userid, String password) {
		// TODO Auto-generated method stub
		userMapper.modifyUserPassword(userid, password);
	}

}
