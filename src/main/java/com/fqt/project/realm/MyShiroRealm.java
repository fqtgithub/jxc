package com.fqt.project.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.fqt.project.entity.User;
import com.fqt.project.service.UserService;

public class MyShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
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
