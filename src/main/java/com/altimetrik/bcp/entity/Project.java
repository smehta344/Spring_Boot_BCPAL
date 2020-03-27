package com.altimetrik.bcp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Column(name = "account_id")
	private int accountId;
	@OneToOne(mappedBy = "project")
	@JsonIgnore
	private ProjLocLeaderAssoc projLocLeaderAssoc;
	@OneToOne(mappedBy = "project")
	@JsonIgnore
	private DailyStatus dailyStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public ProjLocLeaderAssoc getProjLocLeaderAssoc() {
		return projLocLeaderAssoc;
	}

	public void setProjLocLeaderAssoc(ProjLocLeaderAssoc projLocLeaderAssoc) {
		this.projLocLeaderAssoc = projLocLeaderAssoc;
	}

	public DailyStatus getDailyStatus() {
		return dailyStatus;
	}

	public void setDailyStatus(DailyStatus dailyStatus) {
		this.dailyStatus = dailyStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Project [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append(", projLocLeaderAssoc=");
		builder.append(projLocLeaderAssoc);
		builder.append(", dailyStatus=");
		builder.append(dailyStatus);
		builder.append("]");
		return builder.toString();
	}

}
