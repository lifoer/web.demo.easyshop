package com.es.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.es.mapper.GoodsMapper;
import com.es.pojo.Goods;
import com.es.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public List<Goods> findAllGoods() {
		return goodsMapper.findAllGoods();
	}

	@Override
	public List<Goods> queryCategory(String category) {
		return goodsMapper.queryCategory(category);
	}

	@Override
	public Goods queryGoods(String id) {
		Goods goods = goodsMapper.queryGoods(id);
		return goods;
	}

	@Override
	public Integer findCount() {
		return goodsMapper.findCount();
	}

	@Override
	public List<Goods> findManageGoods() {
		return goodsMapper.findManageGoods();
	}

	@Override
	public String updateGoods(String id, String category, String title, String priceStr, String des,String image) {
		category = tranCategory(category);
		Double price = Double.parseDouble(priceStr);
		Goods goods = new Goods(id, title, price, des, image, category);
		Integer row = goodsMapper.updateGoods(goods);
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (row == 0) {
			map.put("state", 0);
		} else {
			map.put("state", 1);
		}
		String json = JSON.toJSONString(map);
		return json;
	}

	@Override
	public String updateGoods(String id, Integer state) {
		Goods goods = new Goods(id, state);
		Integer row = goodsMapper.updateGoods(goods);
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (row == 0) {
			map.put("state", 0);
		} else {
			map.put("state", 1);
		}
		String json = JSON.toJSONString(map);
		return json;
	}

	@Override
	public String insertGoods(String category, String title, String priceStr, String des,String image) {
		String id = UUID.randomUUID().toString();
		category = tranCategory(category);
		Integer state = 0;
		Double price = Double.parseDouble(priceStr);
		Goods goods = new Goods(id, title, price, des, image, state, category);
		Integer row = goodsMapper.insertGoods(goods);
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (row == 0) {
			map.put("state", 0);
		} else {
			map.put("state", 1);
		}
		String json = JSON.toJSONString(map);
		return json;
	}

	public String tranCategory(String category) {
		if (category.equals("0")) {
			category = "服装";
		} else if (category.equals("1")) {
			category = "数码";
		} else if (category.equals("2")) {
			category = "图书";
		} else if (category.equals("3")) {
			category = "电器";
		} else if (category.equals("4")) {
			category = "饰品";
		} else if (category.equals("5")) {
			category = "食品";
		} else if (category.equals("6")) {
			category = "家居";
		} else {
			category = "默认";
		}
		return category;
	}

	@Override
	public List<Goods> queryGoodsByKey(String keyword) {
		String title = keyword;
		String des = keyword;
		return goodsMapper.queryGoodsByKey(title, des);
	}

}
