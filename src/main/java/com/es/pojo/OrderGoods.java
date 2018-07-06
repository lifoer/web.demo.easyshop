package com.es.pojo;

public class OrderGoods {
	private String id;
	private String goodsId;
	private String orderId;
	private String userId;
	private Integer num;

	public OrderGoods() {
		super();
	}

	public OrderGoods(String id, String goodsId, String orderId, String userId, Integer num) {
		super();
		this.id = id;
		this.goodsId = goodsId;
		this.orderId = orderId;
		this.userId = userId;
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
