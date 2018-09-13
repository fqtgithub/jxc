package com.fqt.project.entity;

public class Log {
	
	private Integer id;
	private String content;
	private String time;
	private String type;
	private User user;
	
	public Log(String content, String time, String type, User user) {
		super();
		this.content = content;
		this.time = time;
		this.type = type;
		this.user = user;
	}
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
