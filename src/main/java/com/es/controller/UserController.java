package com.es.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.es.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("checkLogin")
	public String checkLogin(String username, String password,HttpSession session) {
		return userService.checkLogin(username,password,session);
	}
	
	@RequestMapping("logout") 
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		session.removeAttribute("userId");
		return "redirect:index.html";
	}
	
	@ResponseBody
	@RequestMapping("checkRegister")
	public String checkRegister(String username) {
		return userService.checkRegister(username);
	}
	
	@ResponseBody
	@RequestMapping("insertUser")
	public String insertUser(String username, String password, String phone) {
		return userService.insertUser(username,password,phone);
	}
	
}
