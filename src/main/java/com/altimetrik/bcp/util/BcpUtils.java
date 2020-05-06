package com.altimetrik.bcp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;

import com.altimetrik.bcp.config.BcpPropertyConfig;

public class BcpUtils {
	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}
	
	public static java.sql.Date dateFormatterForAttdPercent(Date date) throws ParseException{
		DateFormat destDf = new SimpleDateFormat("dd/MM/yyyy");
		String convertDate = destDf.format(date);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return  new java.sql.Date(dateFormat.parse(convertDate).getTime());
	}
	
	public static double roundDoubleValue (double value) {
		int precision = 2;
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
	
	public static String getFileExtension(String name) {
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf);
	}
	
	public static List<String> getLeaveDbValues(){
		List<String> attendanceTypeList = new ArrayList<>();
		attendanceTypeList.add(AppConstants.LEAVE);
		attendanceTypeList.add(AppConstants.LEAVE_APPROVAL_PENDING);
		attendanceTypeList.add(AppConstants.FLOATER_HOLIDAY);
		attendanceTypeList.add(AppConstants.FLOATER_HOLIDAY_APPROVAL_PENDING);
		return attendanceTypeList;
	}
	
	public static List<String> convertListOfStringToLowercase(List<String> strings)
	{
	    ListIterator<String> iterator = strings.listIterator();
	    while (iterator.hasNext())
	    {
	        iterator.set(iterator.next().toLowerCase());
	    }
	    return strings;
	}
	
	
	public static boolean attendanceValidation(List<String> list, BcpPropertyConfig itsConfig){
		List<String> allowcountryNameList = itsConfig.getAttendanceAllowCountryNamesList();
		List<String> skipAccNameList = itsConfig.getAttendanceSkipAccountNamesList();
		List<String> skipEmpIdList = itsConfig.getAttendanceSkipEmployeeIdsMatchList();
		
		if(!allowcountryNameList.contains(list.get(5).toString().toLowerCase())){
			return false;
		}
		
		if(skipAccNameList.contains(list.get(4).toString().toLowerCase())){
			return false;
		}
		
		for(String empIdPattern : skipEmpIdList){
			if(list.get(1).toString().toLowerCase().contains(empIdPattern)){
				return false;
			}
		}
		
		return true;
	}
	
}
