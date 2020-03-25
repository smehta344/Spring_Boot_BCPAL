package com.altimetrik.bcp.model;

import java.util.Date;

//@Entity
//@Table
public class BusinessContinuityPlan {
	//@Id
	private Date date;
	private String location;
	private String engineering_Leader;
	private String project;
	private String account;
	private String project_Status;
	private String team_Size;
	private String team_Logged;
	private String milestone;
	private String challenge;
	private String mitigation_Plan;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEngineering_Leader() {
		return engineering_Leader;
	}

	public void setEngineering_Leader(String engineering_Leader) {
		this.engineering_Leader = engineering_Leader;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getProject_Status() {
		return project_Status;
	}

	public void setProject_Status(String project_Status) {
		this.project_Status = project_Status;
	}

	public String getTeam_Size() {
		return team_Size;
	}

	public void setTeam_Size(String team_Size) {
		this.team_Size = team_Size;
	}

	public String getTeam_Logged() {
		return team_Logged;
	}

	public void setTeam_Logged(String team_Logged) {
		this.team_Logged = team_Logged;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	public String getMitigation_Plan() {
		return mitigation_Plan;
	}

	public void setMitigation_Plan(String mitigation_Plan) {
		this.mitigation_Plan = mitigation_Plan;
	}

	@Override
	public String toString() {
		return "BusinessContinuityPlan [date=" + date + ", location=" + location + ", engineering_Leader="
				+ engineering_Leader + ", project=" + project + ", account=" + account + ", project_Status="
				+ project_Status + ", team_Size=" + team_Size + ", team_Logged=" + team_Logged + ", milestone="
				+ milestone + ", challenge=" + challenge + ", mitigation_Plan=" + mitigation_Plan + "]";
	}

}
