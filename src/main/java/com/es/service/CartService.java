package com.es.service;

import java.util.List;
import java.util.Map;

public interface CartService {

	public void insertCart(String goodsId, Integer num, String userId);

	public List<Map<?, ?>> queryCart(String userId);

	public void updateCart(String userId, String goodsId, Integer num);

	public void deleteCart(String userId, String goodsId);

	public void deleteAllCart(String userId);

	public String payOrder(String orderId, String amount);

}
