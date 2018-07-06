package com.es.pojo;

public class OrderInfo {
	private String id;
	private String address;
	private String name;
	private String phone;
	private Integer state;
	private String calendar;
	private Double amount;

	public OrderInfo() {
		super();
	}

	public OrderInfo(String id, String address, String name, String phone, Integer state, String calendar,
			Double amount) {
		super();
		this.id = id;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.state = state;
		this.calendar = calendar;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCalendar() {
		return calendar;
	}

	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
