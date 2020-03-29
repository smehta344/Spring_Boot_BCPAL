package com.altimetrik.bcp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.bcp.dao.AttendanceRepo;
import com.altimetrik.bcp.dao.DailyStatusRepo;
import com.altimetrik.bcp.dao.LeaderRepo;
import com.altimetrik.bcp.dao.ProjectAssocRepo;
import com.altimetrik.bcp.entity.Attendance;
import com.altimetrik.bcp.entity.DailyStatus;
import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;
import com.altimetrik.bcp.entity.Project;
import com.altimetrik.bcp.model.AttendanceData;
import com.altimetrik.bcp.model.PlanDetailFormData;

@Service
public class BCMService {
	@Autowired
	private DailyStatusRepo statusRepo;
	
	@Autowired
	ProjectAssocRepo projecAssocRepo;
	
	@Autowired
	LeaderRepo leaderRepo;
	
	@Autowired
	AttendanceRepo attendenceRepo;
	
	public void createDilyStatus(PlanDetailFormData formaData){
		DailyStatus statusObj = createStatusObj(formaData);
		statusRepo.save(statusObj);
	}
	
	public DailyStatus createStatusObj(PlanDetailFormData formData){
		DailyStatus statusObject = new DailyStatus();
		statusObject.setDate(formData.getDate());
		statusObject.setChallenges(formData.getDeliveryChallenge());
		statusObject.setLocationId(formData.getLocationId());
		statusObject.setProjectId(formData.getProjectId());
		
		//TODO Need to add below attributes with correct format
		statusObject.setCreatedBy("Test");
		statusObject.setCreatedTime(new Date());
		statusObject.setUpdatedBy("TEST");
		statusObject.setUpdatedTime(new Date());
		statusObject.setUpdates("test");
		return statusObject;
	}
	
	public Leader getLeader(int locationId, int accountId){
		Leader leader = new Leader();
		List<ProjLocLeaderAssoc> assocList = projecAssocRepo.findAll();
		for(ProjLocLeaderAssoc leaderAssoc:assocList){
			if((leaderAssoc.getAccount().getId() == accountId) && 
					leaderAssoc.getLocation().getId() == locationId ){
				leader = leaderAssoc.getLeader();
				return leader;
			}
		}
		return leader;
	}
	
	public List<Project> getProjectById(int accountId){
		Object obj = new Object();
		List<ProjLocLeaderAssoc> assocList = projecAssocRepo.findAll();
		List<Project> projList = new ArrayList<Project>();
		for(ProjLocLeaderAssoc projAssoc:assocList){
			if(projAssoc.getAccount().getId() == accountId){
				projList.add(projAssoc.getProject());
			}
		}
		return projList;
	}
	
	public Map<String, AttendanceData> getAttendeceData(String type, Date fromDate){
		java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(fromDate);
		
		List<Attendance> attendanceList = attendenceRepo.getAttendByAccount(dateString);
		System.out.println("attendanceList="+attendanceList);
		List<AttendanceData> attnPercentageData = calculatePercentage(attendanceList);
		System.out.println(attnPercentageData.toString());
		//Map<String, AttendanceData> accountGroupMap = attnPercentageData.stream().collect(Collectors.toMap(AttendanceData::getAccountName, c->c));
		
		Map<String,AttendanceData> finalMap = new TreeMap<>();
		for(AttendanceData data : attnPercentageData){
			if(data.getAccountName() != null)
				finalMap.put(data.getAccountName(), data);
		}
		return finalMap;
	}
	
	public List<AttendanceData> calculatePercentage(List<Attendance> attendenceLst){
		int total;
		int marked;
		int unmarked;
		int leaveCount;
		int leaveApprovalPending;
		int markedPercentage;
		int unmarkedPercentage;
		int leavePercentage;
		List<AttendanceData> attendanceDataList = new ArrayList<AttendanceData>();
		for(Attendance attendance : attendenceLst){
			
				total = attendance.getTotal();
				marked = attendance.getMarked();
				unmarked = attendance.getUnMarked();
				leaveCount = attendance.getLeaveCount();
				leaveApprovalPending = attendance.getLeaveAppPend();
				markedPercentage = (marked * 100 / total);
				unmarkedPercentage = (unmarked * 100 / total);
				leavePercentage = (leaveCount * 100 / total);
				
				AttendanceData attendanceData = new AttendanceData();
				attendanceData.setTotal(total);
				attendanceData.setLeave(leaveCount);
				attendanceData.setMarked(marked);
				attendanceData.setMarked_percent(markedPercentage);
				attendanceData.setUnmarked(unmarked);
				attendanceData.setUnmarked_percent(unmarkedPercentage);
				attendanceData.setLeave_percent(leavePercentage);
				attendanceData.setAccountName(attendance.getAccountName());
				attendanceDataList.add(attendanceData);
		}
		
		return attendanceDataList;
		
	}
	
}
