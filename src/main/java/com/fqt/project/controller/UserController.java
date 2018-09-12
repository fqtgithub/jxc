package com.fqt.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fqt.project.entity.User;
import com.fqt.project.service.UserService;

/**
 * 用户控制层
 * @author eye
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public Map<String,String> login(String userName,String password,String yzm,HttpSession session) {
		Map<String,String> map=new HashMap<String,String>();
		if (yzm.equals(session.getAttribute("yzmStr"))) {
			Subject subject=SecurityUtils.getSubject();
			UsernamePasswordToken token=new UsernamePasswordToken(userName, password);
			try {
				subject.login(token);
				User currentUser=userService.findUserByName(userName);
				session.setAttribute("currentUser", currentUser);
				map.put("result", "success");
			} catch (Exception e) {
				// TODO: handle exception
				map.put("result", "userError");
			}
		}else {
			map.put("result", "yzmError");
		}
		return map;
	}
}
