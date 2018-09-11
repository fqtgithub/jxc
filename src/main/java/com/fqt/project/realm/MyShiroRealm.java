package com.fqt.project.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.fqt.project.service.UserService;

public class MyShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		User loginUser=(User) SecurityUtils.getSubject().getPrincipal();
		User verifyUser=userService.Login(loginUser);
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		List<Role> roles=verifyUser.getRoles();
		Set<String> roleNames=new HashSet<String>();
		for (Role role : roles) {
			roleNames.add(role.getName());
			List<Menu> menus=menuService.getRoleMenus(role.getId());
			for (Menu menu : menus) {
				info.addStringPermission(menu.getName());
			}
		}
		info.setRoles(roleNames);
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		User loginUser=(User) token.getPrincipal();
		User verifyUser=userService.Login(loginUser);
		if (verifyUser!=null) {
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(verifyUser, verifyUser.getPassword(), "jxc");
			return authcInfo;
		} else {

			return null;
		}
	}

}
