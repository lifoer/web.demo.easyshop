package com.es.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.es.pojo.User;

public interface UserService {

	public String checkLogin(String username, String password, HttpSession session);

	public String checkRegister(String username);

	public String insertUser(String username, String password, String phone);
	
	public List<User> findAllUser();

	public Integer findCount();

	public String updateUser(String id,Integer state);

	public String updateUser(String id, String password, String phone, String remark);

	public User queryUser(String id);

}
