package com.mkf.shoppingscreen.models;

public class Items {
	
	private String name;
	private String code;
	private double price;
	private String photo;
	private boolean Confirm;
	
	public String getName() {
		
		return this.name;
	}
	
	public String getCode() {
		
		return this.code;
	}
	
	public double getPrice() {
	
		return this.price;
	}
	
	public String getPhoto() {
	
		return this.photo;
	}

	public void setAll(String n,String c,double p,String pic) {
		
		setName(n);
		setCode(c);
		setPrice(p);
		setPhoto(pic);
	}
	
	public void setName(String n) {
		
		this.name = n;
	}
	
	public void setCode(String c) {
		
		this.code = c;
	}
	
	public void setPrice(double p) {
	
		this.price = p;
	}
	
	public void setPhoto(String pic) {
	
		this.photo = pic;
	}
	
	public String toString() {
		
		return String.format("Name: %s \t Code: %s \t Price: %d \t Photo: %s ", getName(),getCode(),getPrice(),getPhoto());
	}

	public boolean isConfirm() {
		return Confirm;
	}

	public void setConfirm(boolean confirm) {
		Confirm = confirm;
	}
}



