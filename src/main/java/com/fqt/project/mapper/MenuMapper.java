package com.fqt.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.fqt.project.entity.Menu;

/**
 * 权限(菜单)持久层
 * @author eye
 *
 */
public interface MenuMapper {
	
	/**
	 * 查询角色的拥有的menu
	 * @param roleid
	 * @return
	 */
	@Select("select * from t_menu where id in(select menuid from t_role_menu where roleid=#{roleid})")
	List<Menu> getRoleMenus(Integer roleid);

}
