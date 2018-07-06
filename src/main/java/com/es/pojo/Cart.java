package com.es.pojo;

public class Cart {
	private String id;
	private String userId;
	private String goodsId;
	private Integer num;

	public Cart() {
		super();
	}

	public Cart(String id, String userId, String goodsId, Integer num) {
		super();
		this.id = id;
		this.userId = userId;
		this.goodsId = goodsId;
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
