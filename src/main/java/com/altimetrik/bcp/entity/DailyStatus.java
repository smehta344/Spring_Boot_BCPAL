package com.altimetrik.bcp.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "daily_status")
public class DailyStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "date")
	private Date date;
	@Column(name = "team_size")
	private int teamSize;
	@Column(name = "updates")
	private String updates;
	@Column(name = "challenges")
	private String challenges;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_time")
	private Date createdTime;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", referencedColumnName = "id")	
	private Project project;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	public String getUpdates() {
		return updates;
	}

	public void setUpdates(String updates) {
		this.updates = updates;
	}

	public String getChallenges() {
		return challenges;
	}

	public void setChallenges(String challenges) {
		this.challenges = challenges;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DailyStatus [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", teamSize=");
		builder.append(teamSize);
		builder.append(", updates=");
		builder.append(updates);
		builder.append(", challenges=");
		builder.append(challenges);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", createdTime=");
		builder.append(createdTime);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", updatedTime=");
		builder.append(updatedTime);
		builder.append(", project=");
		builder.append(project);
		builder.append(", location=");
		builder.append(location);
		builder.append("]");
		return builder.toString();
	}

}
