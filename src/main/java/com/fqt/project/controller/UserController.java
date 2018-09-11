package com.fqt.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fqt.project.entity.User;

/**
 * 用户控制层
 * @author eye
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/login")
	public ModelAndView login(User user) {
		ModelAndView mav=new ModelAndView();
		return mav;
	}
}
