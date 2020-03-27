package com.altimetrik.bcp.model;

import java.util.List;

import com.altimetrik.bcp.entity.Account;
import com.altimetrik.bcp.entity.Location;


public class PlanDetailEntity {
	List<Location> locationData;
	List<Account> accountData;
	public List<Location> getLocationData() {
		return locationData;
	}
	public void setLocationData(List<Location> locationData) {
		this.locationData = locationData;
	}
	public List<Account> getAccountData() {
		return accountData;
	}
	public void setAccountData(List<Account> accountData) {
		this.accountData = accountData;
	}
	
	
}
