package com.es.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.es.config.AlipayConfig;
import com.es.mapper.CartMapper;
import com.es.pojo.Cart;
import com.es.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;

	@Override
	public void insertCart(String goodsId, Integer num, String userId) {
		Cart cart = cartMapper.checkCart(userId, goodsId);
		if (cart == null) {
			String id = UUID.randomUUID().toString();
			cart = new Cart(id, userId, goodsId, num);
			cartMapper.insertCart(cart);
		} else {
			num = cart.getNum() + 1;
			cartMapper.updateCart(userId, goodsId, num);
		}
	}

	@Override
	public List<Map<?, ?>> queryCart(String userId) {
		return cartMapper.queryCart(userId);
	}

	@Override
	public void updateCart(String userId, String goodsId, Integer num) {
		cartMapper.updateCart(userId, goodsId, num);
	}

	@Override
	public void deleteCart(String userId, String goodsId) {
		cartMapper.deleteCart(userId, goodsId);
	}

	@Override
	public void deleteAllCart(String userId) {
		cartMapper.deleteAllCart(userId);
	}

	@Override
	public String payOrder(String orderId, String amount) {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = URLEncoder.encode(orderId, "UTF-8");
			// 付款金额，必填
			String total_amount = URLEncoder.encode(amount, "UTF-8");
			// 订单名称，必填
			String subject = URLEncoder.encode("order" + orderId, "UTF-8");
			// 商品描述，可空
			String body = URLEncoder.encode("", "UTF-8");
			alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
					+ total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			// 请求
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			// 输出
			result = result.replaceAll("<input type=\\\"submit\\\" value=\\\"立即支付\\\" style=\\\"display:none\\\" >", "")
					.replaceAll("<script>document\\.forms\\[0\\]\\.submit\\(\\);</script>", "").replaceAll("\n", "");
			map.put("data", result);
			String json = JSON.toJSONString(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("data", "null");
			String json = JSON.toJSONString(map);
			return json;
		}
	}
}
