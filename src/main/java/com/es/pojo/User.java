package com.es.pojo;

public class User {
	private String id;
	private String username;
	private String password;
	private String phone;
	private String remark;
	private Integer state;

	public User() {
		super();
	}

	public User(String id, Integer state) {
		super();
		this.id = id;
		this.state = state;
	}

	public User(String id, String password, String phone, String remark) {
		super();
		this.id = id;
		this.password = password;
		this.phone = phone;
		this.remark = remark;
	}

	public User(String id, String username, String password, String phone, String remark, Integer state) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.remark = remark;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
