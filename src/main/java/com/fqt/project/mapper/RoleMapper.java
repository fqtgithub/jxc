package com.fqt.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.fqt.project.entity.Role;

/**
 * 角色持久层
 * @author eye
 *
 */
public interface RoleMapper {
	
	/**
	 * 查询用户拥有的角色
	 * @param userid
	 * @return
	 */
	@Select("select id,name from t_role where id in(select roleid from t_user_role where userid=#{userid})")
	@Results(
			{
				@Result(id=true,column="id",property="id"),
				@Result(column="name",property="name")
			}
	)
	List<Role> getUserRoles(Integer userid);
	
	/**
	 * 查询所有的角色
	 * @return
	 */
	@Select("select id,name,remarks from t_role limit #{start},#{end}")
	List<Role> getAllRoleInfosByPage(@Param("start")Integer start,@Param("end") Integer end);
	
	/**
	 * 查询所有角色的条目数
	 * @return
	 */
	@Select("select count(id) from t_role")
	Long getAllRoleCount();
	
	/**
	 * 角色菜单关联操作
	 * @param roleid
	 * @param menuid
	 */
	@Insert("insert into t_role_menu(menuid,roleid) values(#{menuid},#{roleid})")
	void insertRoleMenus(@Param("roleid") Integer roleid,@Param("menuid") Integer menuid);
	
	/**
	 * 删除角色关联的菜单
	 * @param roleid
	 */
	@Delete("delete from t_role_menu where roleid=#{roleid}")
	void deleteRoleMenus(@Param("roleid") Integer roleid);

}
