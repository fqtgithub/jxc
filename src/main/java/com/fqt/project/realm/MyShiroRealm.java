package com.fqt.project.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.fqt.project.entity.Menu;
import com.fqt.project.entity.Role;
import com.fqt.project.entity.User;
import com.fqt.project.service.MenuService;
import com.fqt.project.service.RoleService;
import com.fqt.project.service.UserService;

public class MyShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String userName=(String) SecurityUtils.getSubject().getPrincipal();
		User verifyUser=userService.findUserByName(userName);
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		List<Role> userRoles=roleService.getUserRoles(verifyUser.getId());
		List<String> userRoleNames=new ArrayList<String>();
		for (Role role : userRoles) {
			userRoleNames.add(role.getName());
			List<Menu> menuList=menuService.getRoleMenus(role.getId());
			for (Menu menu : menuList) {
				info.addStringPermission(menu.getName());
			}
		}
		info.addRoles(userRoleNames);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userName=(String) token.getPrincipal();
		User verifyUser=userService.findUserByName(userName);
		if (verifyUser!=null) {
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(verifyUser.getUserName(), verifyUser.getPassword(), "jxc");
			return authcInfo;
		} else {
			return null;
		}
	}

}
