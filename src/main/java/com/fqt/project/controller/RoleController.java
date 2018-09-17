package com.fqt.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fqt.project.entity.Role;
import com.fqt.project.service.RoleService;

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

	@RequestMapping("/getAllRoleInfos")
	@ResponseBody
	public Map<String,Object> getAllRoleInfos(Integer page,Integer rows){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			long totalItem=roleService.getAllRoleCount();
			List<Role> roleList=roleService.getAllRoleInfosByPage((page-1)*rows,rows*page);
			map.put("rows", roleList);
			map.put("total", totalItem);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return map;
	}
	
}
