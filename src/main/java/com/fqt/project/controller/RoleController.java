package com.fqt.project.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fqt.project.constant.JxcConstant;
import com.fqt.project.entity.Log;
import com.fqt.project.entity.Role;
import com.fqt.project.entity.User;
import com.fqt.project.service.LogService;
import com.fqt.project.service.RoleService;
import com.fqt.project.util.DateUtil;

/**
 * 角色控制层
 * @author eye
 *
 */
@Controller
@RequestMapping("/roleManager")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private LogService logService;

	@RequestMapping("/getAllRoleInfos")
	@ResponseBody
	public Map<String,Object> getAllRoleInfos(Integer page,Integer rows,HttpSession session){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			long totalItem=roleService.getAllRoleCount();
			List<Role> roleList=roleService.getAllRoleInfosByPage((page-1)*rows,rows*page);
			map.put("rows", roleList);
			map.put("total", totalItem);
			User currentUser=(User) session.getAttribute("currentUser");
			String datetime=DateUtil.DatetoString(new Date());
			Log log=new Log(currentUser.getUserName()+"查询角色", datetime, JxcConstant.SEARCH_ACTION, currentUser);
			logService.insertLog(log);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
}
