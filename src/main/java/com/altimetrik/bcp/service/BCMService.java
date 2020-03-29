package com.altimetrik.bcp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.security.auth.x500.X500Principal;

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
		List<Attendance> attenList = createAttendList(attendenceRepo.getAttendByAccount(dateString));
		List<AttendanceData> attnPercentageData = calculatePercentage(attenList);
		Map<String, AttendanceData> accountGroupMap = attnPercentageData.stream().collect(Collectors.toMap(AttendanceData::getAccountName, c->c));
		return accountGroupMap;
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
				attendanceData.setLeave(leavePercentage);
				attendanceData.setAccountName(attendance.getAccountName());
				attendanceDataList.add(attendanceData);
		}
		
		return attendanceDataList;
		
	}
	
	public List<Attendance> createAttendList(List<Object> objList){
		List<Attendance> attenList = new ArrayList<Attendance>();
		Attendance attend = new Attendance();
		Attendance attend1 = new Attendance();
		attend.setAccountName("fis");
		attend.setLeaveAppPend(10);
		attend.setTotal(100);
		attend.setMarked(50);
		attend.setUnMarked(40);
		attenList.add(attend);

		attend1.setAccountName("vissa");
		attend1.setLeaveAppPend(10);
		attend1.setTotal(200);
		attend1.setMarked(50);
		attend1.setUnMarked(40);
		attenList.add(attend1);
		
		return attenList;
	}
	
}
