package com.altimetrik.bcp.entity;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.altimetrik.bcp.model.AttendanceByAccount;
import com.altimetrik.bcp.model.AttendanceByLocation;
import com.altimetrik.bcp.model.DeliverySummary;
import com.altimetrik.bcp.util.MysqlQueryConstants;

@Entity
@SqlResultSetMapping(
	    name = "deliveryMapping",
	    classes = @ConstructorResult(
	            targetClass = DeliverySummary.class,
	            columns = {
	            		@ColumnResult(name = "total",type=Integer.class),
	            		@ColumnResult(name = "account",type=String.class),
	                    @ColumnResult(name = "redCount",type=Integer.class),
	                    @ColumnResult(name = "amberCount",type=Integer.class),
	                    @ColumnResult(name = "greenCount",type=Integer.class),
	            }
	    )
	)
@Table(name = "daily_status")
@NamedNativeQuery(name = "deliverySummaryByDateQuery", resultSetMapping ="deliveryMapping",resultClass = DeliverySummary.class, query=MysqlQueryConstants.DELIVERY_SUMMARY_QUERY)
public class DailyStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "team_size")
	private int teamSize;
	
	@Column(name = "updates")
	private String updates;
	
	@ManyToOne
	private Project project;	

	@Column(name = "location_id")
	private int locationId;
	
	@Column(name = "challenges")
	private String challenges;
	
	@Column(name = "milestone")
	private String milestone;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "mitigation_plan")
	private String mitigationPlan;
	
	@Column(name = "deliverable_of_day")
	private String deliverableOfDay;
	
	@Column(name = "wfh_challenge")
	private String wfhChallenge;
	
	@Column(name = "wfh_mitigation_plan")
	private String wfhMitigationPlan;
	
	@Column(name = "hiring_update")
	private String hiringUpdate;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_time")
	private Date createdTime;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "updated_time")
	private Date updatedTime;	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProjectId(Project project) {
		this.project = project;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}
	
	public String getMitigationPlan() {
		return mitigationPlan;
	}

	public void setMitigationPlan(String mitigationPlan) {
		this.mitigationPlan = mitigationPlan;
	}

	public String getDeliverableOfDay() {
		return deliverableOfDay;
	}

	public void setDeliverableOfDay(String deliverableOfDay) {
		this.deliverableOfDay = deliverableOfDay;
	}

	public String getWfhChallenge() {
		return wfhChallenge;
	}

	public void setWfhChallenge(String wfhChallenge) {
		this.wfhChallenge = wfhChallenge;
	}

	public String getWfhMitigationPlan() {
		return wfhMitigationPlan;
	}

	public void setWfhMitigationPlan(String wfhMitigationPlan) {
		this.wfhMitigationPlan = wfhMitigationPlan;
	}

	public String getHiringUpdate() {
		return hiringUpdate;
	}

	public void setHiringUpdate(String hiringUpdate) {
		this.hiringUpdate = hiringUpdate;
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
		return builder.toString();
	}

}
