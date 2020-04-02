package com.altimetrik.bcp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "daily_status")
public class DailyStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "date")
	private Date date;

	@Column(name = "location_id")
	private int locationId;

	@Column(name = "account_id")
	private int accountId;

	@Column(name = "leader_id")
	private int leaderId;

	@Column(name = "project_id")
	private int projectId;

	@Column(name = "status")
	private String status;

	@Column(name = "updates")
	private String updates;

	@Column(name = "team_size")
	private int teamSize;

	@Column(name = "team_logged")
	private int teamLogged;

	@Column(name = "delivery_on_track")
	private String deliveryOnTrack;

	@Column(name = "target_percent")
	private int targetPercentage;

	@Column(name = "actual_percent")
	private int actualPercentage;

	@Column(name = "milestone")
	private String milestone;

	@Column(name = "delivery_challenges")
	private String deliveryChallenges;

	@Column(name = "wfh_challenges")
	private String wfhChallenges;

	@Column(name = "wfh_mitigation_plan")
	private String wfhMitigationPlan;

	@Column(name = "delivery_mitigation_plan")
	private String deliveryMitigationPlan;

	@Column(name = "hiring_updates")
	private String hiringUpdates;

	@Column(name = "key_deliverables")
	private String keyDeliverable;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "updated_time")
	private Date updatedTime;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTeamLogged() {
		return teamLogged;
	}

	public void setTeamLogged(int teamLogged) {
		this.teamLogged = teamLogged;
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

	public String getDeliveryChallenges() {
		return deliveryChallenges;
	}

	public void setDeliveryChallenges(String deliveryChallenges) {
		this.deliveryChallenges = deliveryChallenges;
	}

	public String getWfhChallenges() {
		return wfhChallenges;
	}

	public void setWfhChallenges(String wfhChallenges) {
		this.wfhChallenges = wfhChallenges;
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

	public String getHiringUpdates() {
		return hiringUpdates;
	}

	public void setHiringUpdates(String hiringUpdates) {
		this.hiringUpdates = hiringUpdates;
	}

	public String getKeyDeliverable() {
		return keyDeliverable;
	}

	public void setKeyDeliverable(String keyDeliverable) {
		this.keyDeliverable = keyDeliverable;
	}

	@Override
	public String toString() {
		return "DailyStatus [id=" + id + ", date=" + date + ", locationId=" + locationId + ", accountId=" + accountId
				+ ", leaderId=" + leaderId + ", projectId=" + projectId + ", status=" + status + ", updates=" + updates
				+ ", teamSize=" + teamSize + ", teamLogged=" + teamLogged + ", deliveryOnTrack=" + deliveryOnTrack
				+ ", targetPercentage=" + targetPercentage + ", actualPercentage=" + actualPercentage + ", milestone="
				+ milestone + ", deliveryChallenges=" + deliveryChallenges
				+ ", wfhChallenges=" + wfhChallenges + ", wfhMitigationPlan=" + wfhMitigationPlan
				+ ", deliveryMitigationPlan=" + deliveryMitigationPlan + ", hiringUpdates=" + hiringUpdates
				+ ", keyDeliverable=" + keyDeliverable + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}

}
