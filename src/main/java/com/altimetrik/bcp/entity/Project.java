package com.altimetrik.bcp.entity;

import javax.persistence.OneToOne;

public class Project {

	private int id;
	private String name;
	@OneToOne(mappedBy = "project")
	private ProjLocLeaderAssoc projLocLeaderAssoc;
	
	@OneToOne(mappedBy = "project")
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
		builder.append(", projLocLeaderAssoc=");
		builder.append(projLocLeaderAssoc);
		builder.append(", dailyStatus=");
		builder.append(dailyStatus);
		builder.append("]");
		return builder.toString();
	}

	

}
