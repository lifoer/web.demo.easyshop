package com.es.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.internal.util.AlipaySignature;
import com.es.config.AlipayConfig;
import com.es.pojo.OrderGoods;
import com.es.service.CartService;
import com.es.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;
	/**
	 * 确认订单，查询购物车信息
	 * @param userId 用户id
	 * @param model
	 * @return 确认订单页面
	 */
	@RequestMapping("ensureOrder")
	public String ensureOrder(String userId, Model model) {
		List<Map<?, ?>> cartList = cartService.queryCart(userId);
		model.addAttribute("cartList", cartList);
		return "order-ensure";
	}
	/**
	 * 提交订单，删除购物车
	 * @param orderId 订单号
	 * @param name 收货信息
	 * @param phone
	 * @param address
	 * @param userId 用户id
	 * @param amount 订单金额
	 * @param model
	 * @return 拼接form提交的json串
	 */
	@ResponseBody
	@RequestMapping("submitOrder")
	public String submitOrder(String orderId, String name, String phone, String address, String userId, String amount,
			Model model) {
		// 插入订单信息表
		orderService.insertOrderInfo(orderId, name, phone, address, amount);
		// 根据购物车插入订单商品表
		List<Map<?, ?>> cartList = cartService.queryCart(userId);
		for (Map<?, ?> map : cartList) {
			String goodsId = (String) map.get("goodsId");
			Integer num = (Integer) map.get("num");
			String id = UUID.randomUUID().toString();
			OrderGoods orderGoods = new OrderGoods(id, goodsId, orderId, userId, num);
			orderService.insertOrderGoods(orderGoods);
		}
		cartService.deleteAllCart(userId);
		return cartService.payOrder(orderId,amount);
	}
	/**
	 * 查询订单
	 * @param userId 用户id
	 * @param session 
	 * @param model
	 * @return 当前用户订单列表
	 */
	@RequestMapping("order")
	public String queryOrder(String userId, HttpSession session, Model model) {
		if (userId == null) {
			Object obj = session.getAttribute("userId");
			if (obj != null) {
				userId = (String) obj;
			}
		}
		if(userId != null) {
			List<Map<?, ?>> orderList = orderService.queryOrder(userId);
			for (Map<?, ?> map : orderList) {
				System.out.println(map.get("orderId"));
			}
			model.addAttribute("orderList", orderList);
		}
		return "order";
	}
	/**
	 * 支付宝同步通知
	 * @return 重定向到订单页面
	 */
	@RequestMapping("returnUrl")
	public String returnUrtl() {
		return "redirect:order.html";
	}
	
	/**
	 * 支付宝异步通知
	 * @param request
	 * @return 返回json字符串
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("notifyUrl")
	public String notifyUrl(HttpServletRequest request) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
					AlipayConfig.charset, AlipayConfig.sign_type); // 调用SDK验证签名

			if (signVerified) {// 验证成功
				// 商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

				// 交易金额
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					orderService.updateOrderInfo(out_trade_no);
					orderService.insertOrderPay(out_trade_no, trade_no, total_amount);
				}
				return "success";
			} else {// 验证失败
				return "fail";
				// 调试用，写文本函数记录程序运行情况是否正常
				// String sWord = AlipaySignature.getSignCheckContentV1(params);
				// AlipayConfig.logResult(sWord);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	/**
	 * 暂未使用
	 * @param orderId 订单号
	 * @param amount 支付金额
	 * @return 拼接form提交的json串
	 */
	@RequestMapping("payOrder")
	@ResponseBody
	public String payOrder(String orderId, String amount) {
		return cartService.payOrder(orderId,amount);
	}

}
