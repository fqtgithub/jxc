package com.fqt.project.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fqt.project.entity.Menu;
import com.fqt.project.mapper.MenuMapper;
@Service("menuService")
public class MenuServiceImpl implements MenuMapper {
	
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> getRoleMenus(Integer roleid) {
		// TODO Auto-generated method stub
		return menuMapper.getRoleMenus(roleid);
	}

}
