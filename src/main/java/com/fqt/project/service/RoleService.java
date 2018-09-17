package com.fqt.project.service;

import java.util.List;

import com.fqt.project.entity.Role;

public interface RoleService {

	List<Role> getAllRoleInfosByPage(Integer start,Integer end);
	
	Long getAllRoleCount();
}
