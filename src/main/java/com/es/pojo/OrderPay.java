package com.es.pojo;

public class OrderPay {

	private String orderId;
	private String payId;
	private Double amount;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public OrderPay() {
		super();
	}

	public OrderPay(String orderId, String payId, Double amount) {
		super();
		this.orderId = orderId;
		this.payId = payId;
		this.amount = amount;
	}

}
