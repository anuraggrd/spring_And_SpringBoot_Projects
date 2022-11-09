package com.SpringBoot.reflectionAPI.bean;

public class Item {

	private int item;
	private int amount;
	private String name;
	private double regularPrice;
	private String itemState;
	private boolean couponApplicable;

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(int item, int amount, String name, double regularPrice, String itemState, boolean couponApplicable) {
		super();
		this.item = item;
		this.amount = amount;
		this.name = name;
		this.regularPrice = regularPrice;
		this.itemState = itemState;
		this.couponApplicable = couponApplicable;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int ammount) {
		this.amount = ammount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}

	public String getItemState() {
		return itemState;
	}

	public void setItemState(String itemState) {
		this.itemState = itemState;
	}

	public boolean isCouponApplicable() {
		return couponApplicable;
	}

	public void setCouponApplicable(boolean couponApplicable) {
		this.couponApplicable = couponApplicable;
	}

	@Override
	public String toString() {
		return "Item [item=" + item + ", ammount=" + amount + ", name=" + name + ", regularPrice=" + regularPrice
				+ ", itemState=" + itemState + ", couponApplicable=" + couponApplicable + "]";
	}

}
