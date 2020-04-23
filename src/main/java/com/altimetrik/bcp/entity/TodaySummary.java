package com.altimetrik.bcp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "today_summary")
public class TodaySummary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(
			  shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "IST", locale = "en_GB")
	@Column(name = "date")
	private Date date;
	
	@Column(name = "summary")
	private String summary;
	
	@Column(name = "submitted_by")
	private String submittedby;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSubmittedby() {
		return submittedby;
	}

	public void setSubmittedby(String submittedby) {
		this.submittedby = submittedby;
	}

	@Override
	public String toString() {
		return "TodaySummary [date=" + date + ", summary=" + summary + ", submittedby=" + submittedby + "]";
	}

}
