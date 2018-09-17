package com.fqt.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fqt.project.entity.Role;
import com.fqt.project.mapper.RoleMapper;
import com.fqt.project.service.RoleService;

/**
 * 角色服务层
 * @author eye
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getAllRoleInfosByPage(Integer start,Integer end) {
		// TODO Auto-generated method stub
		return roleMapper.getAllRoleInfosByPage(start,end);
	}

	@Override
	public Long getAllRoleCount() {
		// TODO Auto-generated method stub
		return roleMapper.getAllRoleCount();
	}

}
