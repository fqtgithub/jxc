package com.fqt.project.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fqt.project.constant.JxcConstant;
import com.fqt.project.entity.Log;
import com.fqt.project.entity.Menu;
import com.fqt.project.entity.Role;
import com.fqt.project.entity.User;
import com.fqt.project.service.LogService;
import com.fqt.project.service.MenuService;
import com.fqt.project.service.UserService;
import com.fqt.project.util.DateUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
	@Autowired
	private LogService logService;
	@Autowired
	private MenuService menuService;

	/**
	 * 登录系统
	 * @param userName
	 * @param password
	 * @param yzm
	 * @param session
	 * @return
	 */
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
				String datetime=DateUtil.DatetoString(new Date());
				Log log=new Log(currentUser.getUserName()+"登录了系统", datetime, JxcConstant.LOGIN_ACTION, currentUser);
				logService.insertLog(log);
			} catch (Exception e) {
				// TODO: handle exception
				map.put("result", "userError");
			}
		}else {
			map.put("result", "yzmError");
		}
		return map;
	}
	
	/**
	 * 加载当前用户姓名
	 * @param session
	 * @return
	 */
	@RequestMapping("/loadingUserInfo")
	@ResponseBody
	public String loadingUserInfo(HttpSession session){
		User currentUser=(User) session.getAttribute("currentUser");
		return currentUser.getUserName();
	}
	
	/**
	 * 加载用户菜单
	 * @param session
	 * @param pid
	 * @return
	 */
	@RequestMapping("/loadingUserMenus")
	@ResponseBody
	public String loadingUserMenus(HttpSession session,Integer pid){
		User currentUser=(User) session.getAttribute("currentUser");
		List<Role> userRoles=currentUser.getRoles();
		String str="";
		try {
			str = getAllMenuByParentId(pid, userRoles.get(0).getId()).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 查询所有的菜单
	 * @param pid
	 * @param roleid
	 * @return
	 */
	public JsonArray getAllMenuByParentId(Integer pid,Integer roleid) throws Exception {
		JsonArray jsonArray=getMenuByParentId(pid, roleid);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObject=(JsonObject) jsonArray.get(i);
			if("open".equals(jsonObject.get("state").getAsString())){
				continue;
			}else{
				jsonObject.add("children", getAllMenuByParentId(jsonObject.get("id").getAsInt(), roleid));
			}
		}
		return jsonArray;
	}
	
	
	public JsonArray getMenuByParentId(Integer pid,Integer roleid) throws Exception {
		List<Menu> menulist=menuService.getMenusByParentIdAndRoleId(pid, roleid);
		JsonArray jsonArray=new JsonArray();
		for (Menu menu : menulist) {
			JsonObject jsonObject=new JsonObject();
			jsonObject.addProperty("id", menu.getId());
			jsonObject.addProperty("text", menu.getName());
			if (menu.getState()==1) {
				jsonObject.addProperty("state", "closed");
			} else {
				jsonObject.addProperty("state", "open");
			}
			jsonObject.addProperty("iconCls", menu.getIcon());
			JsonObject attributeObject=new JsonObject();
			attributeObject.addProperty("url", menu.getUrl());
			jsonObject.add("attributes", attributeObject); 
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	/**
	 * 修改用户密码
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateUserPassword")
	@ResponseBody
	public Map<String,String> updateUserPassword(String newPassword,HttpSession session){
		Map<String,String> map=new HashMap<String,String>();
		User currentUser=(User) session.getAttribute("currentUser");
		try {
			userService.modifyUserPassword(currentUser.getId(), newPassword);
			map.put("result", "success");
			String dateTime=DateUtil.DatetoString(new Date());
			Log log=new Log(currentUser.getUserName()+"修改了密码", dateTime, JxcConstant.UPDATE_ACTION, currentUser);
			logService.insertLog(log);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("result", "error");
		}
		return map;
	}
}
