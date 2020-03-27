package com.altimetrik.bcp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name")
	private String name;
	@OneToOne(mappedBy = "location")
	@JsonIgnore
	private ProjLocLeaderAssoc projLocLeaderAssoc;
	@OneToOne(mappedBy = "location")
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
		builder.append("Location [id=");
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
