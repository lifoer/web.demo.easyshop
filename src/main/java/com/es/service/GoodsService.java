package com.es.service;

import java.util.List;

import com.es.pojo.Goods;

public interface GoodsService {

	public List<Goods> findAllGoods();

	public List<Goods> queryCategory(String category);

	public Goods queryGoods(String id);
	
	public Integer findCount();
	
	public List<Goods> findManageGoods();

	public String updateGoods(String id, String category, String title, String price, String des, String image);

	public String updateGoods(String id, Integer state);

	public String insertGoods(String category, String title, String price, String des, String image);

	public List<Goods> queryGoodsByKey(String keyword);

}
