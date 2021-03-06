package com.altimetrik.bcp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PlanDetailFormData {
	@JsonFormat(pattern="MM/dd/yyyy")
	Date date;
	int locationId;
	int accountId;
	int leaderId;
	int projectId;
	String status;
	Float teamSize;
	Float teamlogedCount;
	String deliveryOnTrack;
	int targetPercentage;
	int actualPercentage;
	String milestone;
	String deliveryChallenge;
	String wfhMitigationPlan;
	String deliveryMitigationPlan;
	String wfhChallenge;
	String keyDeliverable;
	String projectName;
	String hiringUpdate;
	String remarks;
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getHiringUpdate() {
		return hiringUpdate;
	}
	public void setHiringUpdate(String hiringupdate) {
		this.hiringUpdate = hiringupdate;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Float getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(Float teamSize) {
		this.teamSize = teamSize;
	}
	public String getDeliveryOnTrack() {
		return deliveryOnTrack;
	}
	public void setDeliveryOnTrack(String deliveryOnTrack) {
		this.deliveryOnTrack = deliveryOnTrack;
	}
	public int getTargetPercentage() {
		return targetPercentage;
	}
	public void setTargetPercentage(int targetPercentage) {
		this.targetPercentage = targetPercentage;
	}
	public int getActualPercentage() {
		return actualPercentage;
	}
	public void setActualPercentage(int actualPercentage) {
		this.actualPercentage = actualPercentage;
	}
	public String getMilestone() {
		return milestone;
	}
	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}
	public String getDeliveryChallenge() {
		return deliveryChallenge;
	}
	public void setDeliveryChallenge(String deliveryChallenge) {
		this.deliveryChallenge = deliveryChallenge;
	}
	public String getWfhChallenge() {
		return wfhChallenge;
	}
	public void setWfhChallenge(String wfhChallenge) {
		this.wfhChallenge = wfhChallenge;
	}
	public String getKeyDeliverable() {
		return keyDeliverable;
	}
	public void setKeyDeliverable(String keyDeliverable) {
		this.keyDeliverable = keyDeliverable;
	}
	public String getWfhMitigationPlan() {
		return wfhMitigationPlan;
	}
	public void setWfhMitigationPlan(String wfhMitigationPlan) {
		this.wfhMitigationPlan = wfhMitigationPlan;
	}
	
	public String getDeliveryMitigationPlan() {
		return deliveryMitigationPlan;
	}
	public void setDeliveryMitigationPlan(String deliveryMitigationPlan) {
		this.deliveryMitigationPlan = deliveryMitigationPlan;
	}
	public Float getTeamlogedCount() {
		return teamlogedCount;
	}
	public void setTeamlogedCount(Float teamlogedCount) {
		this.teamlogedCount = teamlogedCount;
	}
}
