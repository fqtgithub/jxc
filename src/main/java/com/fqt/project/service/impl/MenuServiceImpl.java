package com.fqt.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fqt.project.entity.Menu;
import com.fqt.project.mapper.MenuMapper;
import com.fqt.project.service.MenuService;
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> getRoleMenus(Integer roleid) {
		// TODO Auto-generated method stub
		return menuMapper.getRoleMenus(roleid);
	}

	@Override
	public List<Menu> getMenusByParentIdAndRoleId(Integer pid, Integer roleid) {
		// TODO Auto-generated method stub
		return menuMapper.getMenusByParentIdAndRoleId(pid, roleid);
	}

}
