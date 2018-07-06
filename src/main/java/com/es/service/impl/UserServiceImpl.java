package com.es.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.es.mapper.UserMapper;
import com.es.pojo.User;
import com.es.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String checkLogin(String username, String password,HttpSession session) {
		User user = userMapper.checkLogin(username, password);
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(user == null) {
			map.put("state", 0);
		} else {
			map.put("state", 1);
			session.setAttribute("username", username);
			session.setAttribute("userId", user.getId());
		}
		String json = JSON.toJSONString(map);
		return json;
	}

	@Override
	public String checkRegister(String username) { 
		Integer row = userMapper.checkRegister(username);
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
	public String insertUser(String username, String password, String phone) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		//注册时，再次判断条件,不满足直接返回，满足则插入用户。
		Integer rowNew = userMapper.checkRegister(username);
		if(rowNew > 0) {
			map.put("state", 0);
		} else {
			String id = UUID.randomUUID().toString();
			//是否启用用户，1启动，0停用，-1删除
			Integer state = 1;
			String remark = "";
			User user = new User(id, username, password, phone, remark, state);
			Integer row = userMapper.insertUser(user);
			if(row == 1) {
				map.put("state", 1);
			} else {
				map.put("state", 0);
			}	
		}
		String json = JSON.toJSONString(map);
		return json;
	}

	@Override
	public List<User> findAllUser() {
		return userMapper.findAllUser();
	}

	@Override
	public Integer findCount() {
		return userMapper.findCount();
	}

	@Override
	public String updateUser(String id,Integer state) {
		User user = new User(id,state);
		Integer row = userMapper.updateUser(user);
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
	public String updateUser(String id, String password, String phone, String remark) {
		User user = new User(id,password,phone,remark);
		Integer row = userMapper.updateUser(user);
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
	public User queryUser(String id) {
		return userMapper.queryUser(id);
	}
	
}
