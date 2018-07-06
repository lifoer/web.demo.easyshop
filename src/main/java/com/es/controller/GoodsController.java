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
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	/**
	 * 商品分页查询
	 * @param pageNum 所查页码
	 * @param category 商品分类
	 * @param model
	 * @return 商品集合
	 */
	@RequestMapping("query")
	public String queryCategory(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
			String category, Model model) {
		PageHelper.startPage(pageNum, 60);
		List<Goods> goodsList = goodsService.queryCategory(category);
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	/**
	 * 商品搜索
	 * @param pageNum 所查页码
	 * @param keyword 关键词
	 * @param model
	 * @return 商品集合
	 */
	@RequestMapping("keyword")
	public String queryGoodsByKey(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
			String keyword, Model model) {
		PageHelper.startPage(pageNum, 60);
		List<Goods> goodsList = goodsService.queryGoodsByKey(keyword);
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	/**
	 * 查询单个商品
	 * @param id 商品id
	 * @param model
	 * @return 商品对象
	 */
	@RequestMapping("goods")
	public String queryGoods(String id, Model model) {
		Goods goods = goodsService.queryGoods(id);
		model.addAttribute("goods", goods);
		return "goods";
	}
}
