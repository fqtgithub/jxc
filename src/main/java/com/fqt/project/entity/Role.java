package com.fqt.project.entity;

import java.util.List;

public class Role {
	
	private Integer id;
	private String name;
	private String remarks;
	
	private List<Menu> memus;
	private List<User> users;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<Menu> getMemus() {
		return memus;
	}
	public void setMemus(List<Menu> memus) {
		this.memus = memus;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
