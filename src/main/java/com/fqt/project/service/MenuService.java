package com.fqt.project.service;

import java.util.List;

import com.fqt.project.entity.Menu;

public interface MenuService {

	List<Menu> getRoleMenus(Integer roleid);
}
