package com.es.pojo;

public class Goods {
	private String id;
	private String title;
	private Double price;
	private String comments;
	private String des;
	private String image;
	private Integer state;
	private String category;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Goods() {
	}

	public Goods(String id, Integer state) {
		super();
		this.id = id;
		this.state = state;
	}

	public Goods(String id, String title, Double price, String des, String image, String category) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.des = des;
		this.image = image;
		this.category = category;
	}

	public Goods(String id, String title, Double price, String des, String image, Integer state, String category) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.des = des;
		this.image = image;
		this.state = state;
		this.category = category;
	}

	public Goods(String id, String title, Double price, String comments, String des, String image, Integer state,
			String category) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.comments = comments;
		this.des = des;
		this.image = image;
		this.state = state;
		this.category = category;
	}
}
