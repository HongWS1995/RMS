package com.hong.bean;

public class Product {

	
	private Integer id;
	
	private String name;
	
	private Integer cid;
	
	private Float price;
	
	private String imagepath;
	
	private Character isRemove;
	
	private Category category;
	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getCid() {
		return cid;
	}

	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	
	public Float getPrice() {
		return price;
	}

	
	public void setPrice(Float price) {
		this.price = price;
	}

	
	public String getImagepath() {
		return imagepath;
	}

	
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}


	
	public Character getIsRemove() {
		return isRemove;
	}


	public void setIsRemove(Character isRemove) {
		this.isRemove = isRemove;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
}