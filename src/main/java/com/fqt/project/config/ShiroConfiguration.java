package com.fqt.project.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.fqt.project.realm.MyShiroRealm;

public class ShiroConfiguration {
	
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm=new MyShiroRealm();
		return myShiroRealm;
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager dwsm=new DefaultWebSecurityManager();
		dwsm.setRealm(myShiroRealm());
		return dwsm;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/jxc/login.html");
		
		Map<String,String> filterChainDefinitionMap=new HashMap<String,String>();
		
		/**
		 * 放过的请求路径
		 */
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/jxc/drawYzm", "anon");
		
		/**
		 * 退出的请求路径
		 */
		filterChainDefinitionMap.put("/logout", "logout");
		
		/**
		 * 拦截其他的请求路径
		 */
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}
	
	/**
	 * shiro生命周期管理
	 * @return
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		 DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
	     advisorAutoProxyCreator.setProxyTargetClass(true);
	     return advisorAutoProxyCreator;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

}
