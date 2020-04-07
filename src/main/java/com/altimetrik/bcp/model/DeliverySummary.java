package com.altimetrik.bcp.model;

public class DeliverySummary {
	int total;
	String account;
	int redCount;
	public DeliverySummary(int total, String account, int redCount, int amberCount, int greenCount) {
		super();
		this.total = total;
		this.account = account;
		this.redCount = redCount;
		this.amberCount = amberCount;
		this.greenCount = greenCount;
	}
	int amberCount;
	int greenCount;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getRedCount() {
		return redCount;
	}
	public void setRedCount(int redCount) {
		this.redCount = redCount;
	}
	public int getAmberCount() {
		return amberCount;
	}
	public void setAmberCount(int amberCount) {
		this.amberCount = amberCount;
	}
	public int getGreenCount() {
		return greenCount;
	}
	public void setGreenCount(int greenCount) {
		this.greenCount = greenCount;
	}
	
}
