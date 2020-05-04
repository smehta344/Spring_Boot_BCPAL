package com.altimetrik.bcp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public class TodaySummaryDto {

	@JsonFormat(pattern="MM/dd/yyyy")
	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("todaysummary")
	private String todaySummary;
	
	@JsonProperty("submittedby")
	private String submittedby;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTodaySummary() {
		return todaySummary;
	}

	public void setTodaySummary(String todaySummary) {
		this.todaySummary = todaySummary;
	}

	public String getSubmittedby() {
		return submittedby;
	}

	public void setSubmittedby(String submittedby) {
		this.submittedby = submittedby;
	}

	@Override
	public String toString() {
		return "TodaySummaryDto [date=" + date + ", todaySummary=" + todaySummary + ", submittedby=" + submittedby
				+ "]";
	}

}
