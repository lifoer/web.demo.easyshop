package com.es.mapper;

import java.util.List;
import java.util.Map;

import com.es.pojo.OrderGoods;
import com.es.pojo.OrderInfo;
import com.es.pojo.OrderPay;

public interface OrderMapper {

	public void insertOrderGoods(OrderGoods orderGoods);

	public void insertOrderInfo(OrderInfo orderInfo);

	public List<Map<?, ?>> queryOrder(String userId);

	public void updateOrderInfo(String id);

	public void insertOrderPay(OrderPay orderPay);

}
