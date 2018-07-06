package com.es.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.es.pojo.Admin;

public interface AdminService {
	public String checkLogin(String username, String password, HttpSession session);

	public List<Admin> findAllAdmin();

	public Integer findCount();

	public String checkName(String username);

	public String updateAdminState(String id, Integer state);
	
	public Admin queryAdmin(String id);

	public String updateAdmin(String id, String password, String phone, String role, String remark);

	public String insertAdmin(String username, String password, String phone, String role, String remark);

}
