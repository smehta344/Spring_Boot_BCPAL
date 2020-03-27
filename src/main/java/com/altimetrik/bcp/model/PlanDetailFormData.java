package com.altimetrik.bcp.model;

import java.util.Date;

public class PlanDetailFormData {
	Date date;
	int locationId;
	int accountId;
	int leaderId;
	int projectId;
	String status;
	int teamSize;
	int loogedCount;
	String DeliveryOnTrack;
	int targetPercentage;
	int actualPercentage;
	String milestone;
	String deliveryChallenge;
	String mitigationPlan;
	String wfhChallenge;
	String keyDeliverable;
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
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	public int getLoogedCount() {
		return loogedCount;
	}
	public void setLoogedCount(int loogedCount) {
		this.loogedCount = loogedCount;
	}
	public String getDeliveryOnTrack() {
		return DeliveryOnTrack;
	}
	public void setDeliveryOnTrack(String deliveryOnTrack) {
		DeliveryOnTrack = deliveryOnTrack;
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
	public String getMitigationPlan() {
		return mitigationPlan;
	}
	public void setMitigationPlan(String mitigationPlan) {
		this.mitigationPlan = mitigationPlan;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlanDetailFormData [date=");
		builder.append(date);
		builder.append(", locationId=");
		builder.append(locationId);
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append(", leaderId=");
		builder.append(leaderId);
		builder.append(", projectId=");
		builder.append(projectId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", teamSize=");
		builder.append(teamSize);
		builder.append(", loogedCount=");
		builder.append(loogedCount);
		builder.append(", DeliveryOnTrack=");
		builder.append(DeliveryOnTrack);
		builder.append(", targetPercentage=");
		builder.append(targetPercentage);
		builder.append(", actualPercentage=");
		builder.append(actualPercentage);
		builder.append(", milestone=");
		builder.append(milestone);
		builder.append(", deliveryChallenge=");
		builder.append(deliveryChallenge);
		builder.append(", mitigationPlan=");
		builder.append(mitigationPlan);
		builder.append(", wfhChallenge=");
		builder.append(wfhChallenge);
		builder.append(", keyDeliverable=");
		builder.append(keyDeliverable);
		builder.append("]");
		return builder.toString();
	}
	
}
