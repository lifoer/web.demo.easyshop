package com.es.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.es.mapper.AdminMapper;
import com.es.pojo.Admin;
import com.es.pojo.Goods;
import com.es.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public String checkLogin(String username, String password, HttpSession session) {
		Admin admin = adminMapper.checkLogin(username, password);
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(admin == null) {
			map.put("state", 0);
		} else {
			map.put("state", 1);
			session.setAttribute("adminName", username);
			session.setAttribute("adminId", admin.getId());
			session.setAttribute("adminRole", admin.getRole());
		}
		String json = JSON.toJSONString(map);
		return json;
	}

	@Override
	public List<Admin> findAllAdmin() {
		return adminMapper.findAllAdmin();
	}
	
	@Override
	public Integer findCount() {
		return adminMapper.findCount();
	}

	@Override
	public String checkName(String username) {
		Integer row = adminMapper.checkName(username);
		if(row == 0) {
			return "true";
		} else {
			return "false";
		}
	}

	@Override
	public String updateAdminState(String id, Integer state) {
		Admin admin = new Admin(id,state);
		Integer row = adminMapper.updateAdmin(admin);
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(row == 0) {
			map.put("state", 0);
		} else {
			map.put("state", 1);
		}
		String json = JSON.toJSONString(map);
		return json;
	}

	@Override
	public Admin queryAdmin(String id) {
		return adminMapper.queryAdmin(id);
	}

	@Override
	public String updateAdmin(String id, String password, String phone, String role, String remark) {
		role = tranRole(role);
		Admin admin = new Admin(id, password, phone, role, remark);
		Integer row = adminMapper.updateAdmin(admin);
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(row == 0) {
			map.put("state", 0);
		} else {
			map.put("state", 1);
		}
		String json = JSON.toJSONString(map);
		return json;
	}
	
	public String tranRole(String role) {
		if (role.equals("0")) {
			role = "超级管理员";
		} else if (role.equals("1")) {
			role = "管理员";
		} 
		return role;
	}

	@Override
	public String insertAdmin(String username, String password, String phone, String role, String remark) {
		String id = UUID.randomUUID().toString();
		role = tranRole(role);
		Integer state = 0;
		Admin admin = new Admin(id, username, password, phone, role, remark, state);
		Integer row = adminMapper.insertAdmin(admin);
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (row == 0) {
			map.put("state", 0);
		} else {
			map.put("state", 1);
		}
		String json = JSON.toJSONString(map);
		return json;
	}

}
