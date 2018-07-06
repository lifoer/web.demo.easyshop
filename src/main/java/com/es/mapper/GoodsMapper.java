package com.es.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.es.pojo.Goods;

public interface GoodsMapper {

	public Integer insertGoods(Goods goods);
	
	public List<Goods> findAllGoods();

	public List<Goods> queryCategory(String category);

	public Goods queryGoods(String id);
	
	public Integer findCount();
	
	public List<Goods> findManageGoods();

	public Integer updateGoods(Goods goods);

	public List<Goods> queryGoodsByKey(@Param(value = "title") String title, @Param(value = "des") String des);
	
}
