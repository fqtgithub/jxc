package com.fqt.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fqt.project.entity.Role;
import com.fqt.project.mapper.RoleMapper;
import com.fqt.project.service.RoleMenuService;
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
	@Autowired
	private RoleMenuService roleMenuService;

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

	@Override
	public List<Role> getUserRoles(Integer userid) {
		// TODO Auto-generated method stub
		return roleMapper.getUserRoles(userid);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateRoleMenus(Integer roleid, int[] menuids) {
		// TODO Auto-generated method stub
		roleMenuService.deleteRoleMenus(roleid);
		for (int menuid : menuids) {
			roleMapper.insertRoleMenus(roleid, menuid);
		}
	}

}
