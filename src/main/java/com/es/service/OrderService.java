package com.es.service;

import java.util.List;
import java.util.Map;

import com.es.pojo.OrderGoods;

public interface OrderService {

	public void insertOrderGoods(OrderGoods orderGoods);

	public void insertOrderInfo(String orderId, String name, String phone, String address, String amount);

	public List<Map<?, ?>> queryOrder(String userId);

	public void updateOrderInfo(String id);

	public void insertOrderPay(String orderId, String payId, String amount);

}
