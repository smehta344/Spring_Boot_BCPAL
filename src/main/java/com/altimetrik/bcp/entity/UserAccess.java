package com.altimetrik.bcp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_access")
public class UserAccess {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "is_manager")
	private String isManager;
	
	@Column(name = "is_admin")
	private String isAdmin;
	
	@Column(name = "access_to_summary")
	private String haveAccessToAddSummary;
	
	@Column(name = "access_to_uploadfile")
	private String haveAccessToFileUpload;
	
	@Column(name = "user_status")
	private String status;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String isManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	public String isAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String haveAccessToAddSummary() {
		return haveAccessToAddSummary;
	}

	public void setHaveAccessToAddSummary(String haveAccessToAddSummary) {
		this.haveAccessToAddSummary = haveAccessToAddSummary;
	}

	public String haveAccessToFileUpload() {
		return haveAccessToFileUpload;
	}

	public void setHaveAccessToFileUpload(String haveAccessToFileUpload) {
		this.haveAccessToFileUpload = haveAccessToFileUpload;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserAccess [userId=" + userId + ", isManager=" + isManager + ", isAdmin=" + isAdmin
				+ ", haveAccessToAddSummary=" + haveAccessToAddSummary + ", haveAccessToFileUpload="
				+ haveAccessToFileUpload + ", status=" + status + "]";
	}

}
