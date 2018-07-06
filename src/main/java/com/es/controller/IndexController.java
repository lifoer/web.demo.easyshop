package com.es.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.es.pojo.Goods;
import com.es.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class IndexController {
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("index")
	public String index(@RequestParam(required = false, defaultValue = "1", value = "pageNum")Integer pageNum, Model model) {
		PageHelper.startPage(pageNum, 60);
		List<Goods> goodsList = goodsService.findAllGoods();
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("register")
	public String register() {
		return "register";
	}
	
}

