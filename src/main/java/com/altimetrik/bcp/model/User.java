package com.altimetrik.bcp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {
	@Id
	private String serialNo;
	private String userName;
	private String pwd;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [serialNo=");
		builder.append(serialNo);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(pwd);
		builder.append("]");
		return builder.toString();
	}

}
