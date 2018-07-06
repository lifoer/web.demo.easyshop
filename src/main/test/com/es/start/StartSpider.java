package com.es.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import com.es.spider.DdSpider;

@Controller
public class StartSpider {

	private static DdSpider ddSpider;

	public DdSpider getDdSpider() {
		return ddSpider;
	}

	@Autowired
	public void setDdSpider(DdSpider ddSpider) {
		StartSpider.ddSpider = ddSpider;
	}

	public static void main(String[] args) {
		@SuppressWarnings({ "unused", "resource" })
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"classpath:spring/applicationContext*.xml");
		// 服装
		ddSpider.insertDdGoods("短袖", "服装");
		ddSpider.insertDdGoods("牛仔裤", "服装");
		// 数码
		ddSpider.insertDdGoods("手机", "数码");
		ddSpider.insertDdGoods("笔记本", "数码");
		// 图书
		ddSpider.insertDdGoods("诗集", "图书");
		ddSpider.insertDdGoods("计算机", "图书");
		// 电器
		ddSpider.insertDdGoods("电水壶", "电器");
		ddSpider.insertDdGoods("洗衣机", "电器");
		// 饰品
		ddSpider.insertDdGoods("项链", "饰品");
		ddSpider.insertDdGoods("戒指", "饰品");
		// 食品
		ddSpider.insertDdGoods("干果", "食品");
		ddSpider.insertDdGoods("饼干", "食品");
		// 家居
		ddSpider.insertDdGoods("床品件套", "家居");
		ddSpider.insertDdGoods("简易衣柜", "家居");

		System.out.println("完成！");
	}
}
