package com.altimetrik.bcp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class BusinessContinuityPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int serialNo;
	@JsonFormat(pattern="dd-MM-yyyy")
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

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

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
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessContinuityPlan [serialNo=");
		builder.append(serialNo);
		builder.append(", date=");
		builder.append(date);
		builder.append(", location=");
		builder.append(location);
		builder.append(", engineering_Leader=");
		builder.append(engineering_Leader);
		builder.append(", project=");
		builder.append(project);
		builder.append(", account=");
		builder.append(account);
		builder.append(", project_Status=");
		builder.append(project_Status);
		builder.append(", team_Size=");
		builder.append(team_Size);
		builder.append(", team_Logged=");
		builder.append(team_Logged);
		builder.append(", milestone=");
		builder.append(milestone);
		builder.append(", challenge=");
		builder.append(challenge);
		builder.append(", mitigation_Plan=");
		builder.append(mitigation_Plan);
		builder.append("]");
		return builder.toString();
	}

}
