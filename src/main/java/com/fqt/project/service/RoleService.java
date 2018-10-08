package com.fqt.project.service;

import java.util.List;

import com.fqt.project.entity.Role;

public interface RoleService {
	
	List<Role> getUserRoles(Integer userid);

	List<Role> getAllRoleInfosByPage(Integer start,Integer end);
	
	Long getAllRoleCount();
	
	void updateRoleMenus(Integer roleid,int[] menuids);
}
