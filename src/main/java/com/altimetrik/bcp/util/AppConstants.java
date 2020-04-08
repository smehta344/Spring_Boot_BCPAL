package com.altimetrik.bcp.util;

public class AppConstants {

	public static final int NO_OF_DAYS_TO_FETCH_ATTENDANCE_PERCENT = 7;
	public static final String LEAVE = "Leave";
	public static final String LEAVE_APPROVAL_PENDING = "Leave - Approval Pending";
	public static final String FLOATER_HOLIDAY = "Floater Holiday";
	public static final String FLOATER_HOLIDAY_APPROVAL_PENDING = "Floater Holiday - Approval Pending";
	public static final String ALL = "all";
	public static final String ORGANISATION_WIDE = "ORGANISATION WIDE";
	public static final String MAIL_SUBJECT_FOR_NOTIFY_ATTENDANCE_NOT_MARKED = "ALERT: Mark Your Attendance in EmployWise!";
	public static final String MAIL_CONTENT_FOR_NOTIFY_ATTENDANCE_NOT_MARKED_1ST = "<body style=\"margin: 0; padding: 0;\">"
								+ "<table id=\"page\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
								+ "<tr><td><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"75%\" "
								+ "style=\"border-collapse: collapse;\"><tr> <td style=\"text-align:center;padding:20px;\"> "
								+ " <img height=\"60\" width=\"180\" src=\"cid:Logo.png\" "
								+ "style=\"text-align:center;display: block;  margin-left: auto;  margin-right: auto;  \"> "
								+ "</td></tr></table>"
								//+ "<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"75%\" style=\"border-collapse: collapse;\">"
								//+ "<tr> <td><hr size=\"1\" color=\"#F26522\"> </td></tr></table>"
								+ "<table  id=\"content\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"75%\" style=\"border-collapse: collapse;\">"
								+ "<tr><td><div style=\"font-size: 14px; line-height: 1.2; color: #555555; "
								+ "font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 17px;\">"
								+ "<p style=\"line-height: 1.2;word-spacing: 4px;font-size:16px;color:black;\">Dear ";
	public static final String MAIL_CONTENT_FOR_NOTIFY_ATTENDANCE_NOT_MARKED_2ST = ",</p>"
								+ "<br/>"
								+ "<p style=\"line-height: 1.2;word-spacing: 4px;font-size:16px;color:black;\">"
								+ "You didnt mark your attendance today.<br/> <br/> "
								+ "Please mark your attendance in <a href=\"https://altimetrik.myemploywise.com/\" "
								+ "rel=\"noopener\" style=\"text-decoration: underline; color: #0068A5;\" target=\"_blank\">EmployeWise</a></p>"
								+ "<br/>"
								+ "<p style=\"line-height: 1.2;word-spacing: 4px;font-size:16px;color:black;\">Thanks,</p>"
								+ "<p style=\"line-height: 1.2;word-spacing: 4px;font-size:16px;color:black;\">Altimetrik-BCP Team</p></div>"
								+ "</td></tr></table>";
	
}
