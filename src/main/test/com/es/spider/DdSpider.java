package com.es.spider;

import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.es.mapper.GoodsMapper;
import com.es.pojo.Goods;

@Service
public class DdSpider {

	@Autowired
	private GoodsMapper goodsMapper;

	private final static Logger logger = Logger.getLogger(DdSpider.class);

	public void insertDdGoods(String keyword, String category) {
		int page = 1;
		DdCrawler(keyword, page, category);
	}

	private void DdCrawler(String keyword, int page, String category) {
		String url = "http://search.dangdang.com/?key=" + keyword + "&act=input&page_index=" + page
				+ "&sort_type=sort_sale_amt_desc&show=big&show_shop=0#J_tab";
		try {
			Connection connect = Jsoup.connect(url);
			connect.header("Accept-Encoding", "gzip, deflate, sdch");
			connect.header("Accept-Language", "zh-CN,zh;q=0.8");
			connect.header("Connection", "keep-alive");
			connect.header("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36 LBBROWSER");
			Document doc = connect.get();
			// System.out.println(doc);
			for (int i = 0; i < 60; i++) {
				try {
					// 商品名称
					Element element1 = doc.select("#search_nature_rg ul li .name a").get(i);
					String title = element1.attr("title").substring(1);
					// 商品价格
					Element element2 = doc.select("#search_nature_rg ul li .price span:nth-child(1)").get(i);
					Double price = Double.parseDouble(element2.text().substring(1));
					// 商品简介
					Element element3 = doc.select("#search_nature_rg ul li .search_hot_word").get(i);
					String des = element3.text();
					// 图片链接
					Element element4 = doc.select("#search_nature_rg ul li .pic img").get(i);
					String image = "";
					if (element4.attr("data-original") != null && !element4.attr("data-original").equals("")) {
						image = element4.attr("data-original");
					} else if (element4.attr("src") != null && !element4.attr("src").equals("")
							&& !element4.attr("src").equals("images/model/guan/url_none.png")) {
						image = element4.attr("src");
					}
					// 下载图片到本地
					String dir = "d://easyshop/image";
					// uuid生成图片名称
					String imgName = UUID.randomUUID().toString() + image.substring(image.lastIndexOf("."));
					FileDown.imageDown(image, dir, imgName);
					// 设置图片地址为本地图片服务器地址
					image = "http://image.easyshop.com/" + imgName;
					// 评论数
					Element element5 = doc.select("#search_nature_rg ul li .star a").get(i);
					String comments = element5.text().split("条")[0];
					// 设置id为uuid
					String id = UUID.randomUUID().toString();
					// 设置转态
					Integer state = 1;
					Goods goods = new Goods(id, title, price, comments, des, image, state, category);
					goodsMapper.insertGoods(goods);
				} catch (Exception e) {
					logger.error(e.getStackTrace()[0] + " " + e.toString() + "\n");
					continue;
				}
			}
		} catch (IOException e) {
			logger.error(e.getStackTrace()[0] + " " + e.toString() + "\n");
		}
	}
}
