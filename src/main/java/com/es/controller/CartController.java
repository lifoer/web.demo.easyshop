package com.es.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.es.service.CartService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("cart")
	public String cart(String goodsId, Integer num, String userId, Model model) {
		cartService.insertCart(goodsId, num, userId);
		List<Map<?, ?>> cartList = cartService.queryCart(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}

	@RequestMapping("queryCart")
	public String queryCart(String userId, Model model) {
		List<Map<?, ?>> cartList = cartService.queryCart(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}

	@ResponseBody
	@RequestMapping("updateCart")
	public String updateCart(String goodsId, Integer num, String userId) {
		cartService.updateCart(userId, goodsId, num);
		return "";
	}

	@RequestMapping("deleteCart")
	public String deleteCart(String goodsId, Integer num, String userId, Model model) {
		cartService.deleteCart(userId, goodsId);
		List<Map<?, ?>> cartList = cartService.queryCart(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
}
