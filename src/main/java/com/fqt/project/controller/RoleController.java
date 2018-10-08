package com.fqt.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fqt.project.constant.JxcConstant;
import com.fqt.project.entity.Log;
import com.fqt.project.entity.Menu;
import com.fqt.project.entity.Role;
import com.fqt.project.entity.User;
import com.fqt.project.service.LogService;
import com.fqt.project.service.MenuService;
import com.fqt.project.service.RoleService;
import com.fqt.project.util.DateUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
	@Autowired
	private MenuService menuService;

	@RequestMapping("/getAllRoleInfos")
	@RequiresPermissions(value="角色管理")
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
	
	/**
	 * 请求角色菜单管理窗口
	 * @param pid
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/loadRoleMenuManageDialog")
	@RequiresPermissions(value="角色管理")
	@ResponseBody
	public String loadRoleMenuManageDialog(Integer pid,Integer roleid) {
		List<Menu> roleMenus=menuService.getRoleMenus(roleid);
		List<Integer> roleMenuIds=new ArrayList<Integer>();
		for (Menu menu : roleMenus) {
			roleMenuIds.add(menu.getId());
		}
		//System.out.println(getAllCheckMenuByParentId(pid, roleMenuIds).toString());
		return getAllCheckMenuByParentId(pid, roleMenuIds).toString();
	}
	
	/**
	 * 返回权限设置菜单
	 * @param pid
	 * @param menuIdList
	 * @return
	 */
	public JsonArray getAllCheckMenuByParentId(Integer pid,List<Integer> menuIdList) {
		JsonArray jsonArray=getCheckMenuByParentId(pid, menuIdList);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObject=(JsonObject) jsonArray.get(i);
			if ("open".equals(jsonObject.get("state").toString())) {
				continue;
			} else {
				jsonObject.add("children", getAllCheckMenuByParentId(jsonObject.get("id").getAsInt(), menuIdList));
			}
		}
		return jsonArray;
	}
	
	/**
	 * 获取一层的选中的菜单选项
	 * @param pid
	 * @param menuIdList
	 * @return
	 */
	public JsonArray getCheckMenuByParentId(Integer pid,List<Integer> menuIdList) {
		List<Menu> menuList=menuService.getMenusByParentId(pid);
		JsonArray jsonArray=new JsonArray();
		for (Menu menu : menuList) {
			JsonObject jsonObject=new JsonObject();
			jsonObject.addProperty("id", menu.getId());
			jsonObject.addProperty("text", menu.getName());
			if (menu.getState()==1) {
				jsonObject.addProperty("state", "closed");
			} else {
				jsonObject.addProperty("state", "open");
			}
			jsonObject.addProperty("iconCls", menu.getIcon());
			if (menuIdList.contains(menu.getId())) {
				jsonObject.addProperty("checked", true);
			}
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	/**
	 * 更新角色菜单的关联
	 * @param roleid
	 * @param menuids
	 * @return
	 */
	@RequestMapping("/updateRoleMenus")
	public String updateRoleMenus(@RequestBody Integer roleid,@RequestBody int[] menuids) {
		if (roleid!=null) {
			//roleService.updateRoleMenus(roleid, menuids);
			System.out.println(menuids);
			return "success";
		} else {
			return "error";
		}
		
	}
}
