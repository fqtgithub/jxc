package com.fqt.project.service;

import com.fqt.project.entity.User;

public interface UserService {
	User findUserByName(String userName);
	
	void modifyUserPassword(Integer userid,String password);
}
