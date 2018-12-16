package com.mkf.shoppingscreen.models;

import java.util.List;

public class Ecommerce {
	
	private String ID;
	private String total;
	private String shippingAddress;
	private String Date;
	private List<Items> Items;
	
	
	public String getID() {
		
		return this.ID;
	}
	public String getTotal() {
		
		return this.total;
	}
	public String getShippingAddress() {
		
		return this.shippingAddress;
	}
	
	public void setID(String num) {
		
		this.ID = num;
	}
	public void setTotal(String t) {
		
		this.total = t;
	}
	public void setShippingAddress(String s) {
	
	this.shippingAddress = s;
	}
	
	public List<Items> getItems(){
		
		return this.Items;
	}
	
	public void setItems(List<Items> i){
		
		this.Items = i;
	}


	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
}
