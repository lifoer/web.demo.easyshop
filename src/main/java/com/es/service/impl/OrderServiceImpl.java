package com.es.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.es.mapper.OrderMapper;
import com.es.pojo.OrderGoods;
import com.es.pojo.OrderInfo;
import com.es.pojo.OrderPay;
import com.es.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public void insertOrderGoods(OrderGoods orderGoods) {
		orderMapper.insertOrderGoods(orderGoods);
	}

	@Override
	public void insertOrderInfo(String orderId, String name, String phone, String address,String amountStr) {
		// 支付状态，1已支付，0未支付
		// 后期加入支付功能可更改
		Integer state = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String calendar = dateFormat.format(new Date());
		Double amount = Double.parseDouble(amountStr);
		OrderInfo orderInfo = new OrderInfo(orderId, address, name, phone, state, calendar, amount);
		orderMapper.insertOrderInfo(orderInfo);
	}

	@Override
	public List<Map<?, ?>> queryOrder(String userId) {
		return orderMapper.queryOrder(userId);
	}

	@Override
	public void updateOrderInfo(String id) {
		//更改订单状态为已支付，state=1
		orderMapper.updateOrderInfo(id);
	}

	@Override
	public void insertOrderPay(String orderId, String payId, String amountStr) {
		Double amount = Double.parseDouble(amountStr);
		OrderPay orderPay = new OrderPay(orderId, payId, amount);
		orderMapper.insertOrderPay(orderPay);
	}
}
