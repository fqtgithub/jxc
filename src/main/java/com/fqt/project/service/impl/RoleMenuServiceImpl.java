package com.fqt.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fqt.project.mapper.RoleMapper;
import com.fqt.project.service.RoleMenuService;
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {
	@Autowired
	private RoleMapper roleMapper;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void deleteRoleMenus(Integer roleid) {
		// TODO Auto-generated method stub
		roleMapper.deleteRoleMenus(roleid);
	}

}
