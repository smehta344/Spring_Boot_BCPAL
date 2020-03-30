package com.altimetrik.bcp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.bcp.dao.AttendanceRepo;
import com.altimetrik.bcp.dao.DailyStatusRepo;
import com.altimetrik.bcp.dao.LeaderRepo;
import com.altimetrik.bcp.dao.ProjectAssocRepo;
import com.altimetrik.bcp.entity.DailyStatus;
import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;
import com.altimetrik.bcp.entity.Project;
import com.altimetrik.bcp.model.AttendanceByAccount;
import com.altimetrik.bcp.model.AttendanceByLocation;
import com.altimetrik.bcp.model.AttendanceCommon;
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

	public void createDilyStatus(PlanDetailFormData formaData) {
		DailyStatus statusObj = createStatusObj(formaData);
		statusRepo.save(statusObj);
	}

	public DailyStatus createStatusObj(PlanDetailFormData formData) {
		DailyStatus statusObject = new DailyStatus();
		statusObject.setDate(formData.getDate());
		statusObject.setChallenges(formData.getDeliveryChallenge());
		statusObject.setLocationId(formData.getLocationId());
		statusObject.setProjectId(formData.getProjectId());

		// TODO Need to add below attributes with correct format
		statusObject.setCreatedBy("Test");
		statusObject.setCreatedTime(new Date());
		statusObject.setUpdatedBy("TEST");
		statusObject.setUpdatedTime(new Date());
		statusObject.setUpdates("test");
		return statusObject;
	}

	public Leader getLeader(int locationId, int accountId) {
		Leader leader = new Leader();
		List<ProjLocLeaderAssoc> assocList = projecAssocRepo.findAll();
		for (ProjLocLeaderAssoc leaderAssoc : assocList) {
			if ((leaderAssoc.getAccount().getId() == accountId) && leaderAssoc.getLocation().getId() == locationId) {
				leader = leaderAssoc.getLeader();
				return leader;
			}
		}
		return leader;
	}

	public List<Project> getProjectById(int accountId) {
		List<ProjLocLeaderAssoc> assocList = projecAssocRepo.findAll();
		List<Project> projList = new ArrayList<Project>();
		for (ProjLocLeaderAssoc projAssoc : assocList) {
			if (projAssoc.getAccount().getId() == accountId) {
				projList.add(projAssoc.getProject());
			}
		}
		return projList;
	}

	public Map<String, AttendanceData> getAttendeceData(Date fromDate) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(fromDate);

		List<AttendanceByAccount> attendanceList = attendenceRepo.getAttendByAccount(dateString);
		List<AttendanceData> attnPercentageData = calculatePercentage(attendanceList);
		Map<String, AttendanceData> finalMap = new TreeMap<>();
		for (AttendanceData data : attnPercentageData) {
			if (data.getAccountName() != null)
				finalMap.put(data.getAccountName(), data);
		}
		return finalMap;
	}

	public Map<String, AttendanceData> getAttendeceByLocation(Date fromDate) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(fromDate);

		List<AttendanceByLocation> attendanceList = attendenceRepo.getAttendByLocation(dateString);
		List<AttendanceData> attnPercentageData = calculatePercentage(attendanceList);
		Map<String, AttendanceData> finalMap = new HashMap<>();
		for (AttendanceData data : attnPercentageData) {
			if (data.getLocationName() != null)
				finalMap.put(data.getLocationName(), data);
		}
		return finalMap;
	}

	@SuppressWarnings("unused")
	public List<AttendanceData> calculatePercentage(List<? extends AttendanceCommon> attendenceLst) {
		int total;
		int marked;
		int unmarked;
		int leaveCount;
		int leaveApprovalPending;
		int markedPercentage;
		int unmarkedPercentage;
		int leavePercentage;

		int overAllTotal = 0;
		int overAllMarked = 0;
		int overAllUnMarked = 0;
		int overAllLeave = 0;
		List<AttendanceData> attendanceDataList = new ArrayList<AttendanceData>();
		for (int i = 0; i < attendenceLst.size(); i++) {
			total = attendenceLst.get(i).getTotal();
			marked = attendenceLst.get(i).getMarked();
			unmarked = attendenceLst.get(i).getUnmarked();
			leaveCount = attendenceLst.get(i).getLeave_count();
			leaveApprovalPending = attendenceLst.get(i).getLeave_app_pend();
			markedPercentage = (marked * 100 / total);
			unmarkedPercentage = (unmarked * 100 / total);
			leavePercentage = (leaveCount * 100 / total);

			overAllTotal = overAllTotal + total;
			overAllMarked = overAllMarked + marked;
			overAllUnMarked = overAllUnMarked + unmarked;
			overAllLeave = overAllLeave + leaveCount;

			AttendanceData attendanceData = new AttendanceData();
			attendanceData.setTotal(total);
			attendanceData.setLeave(leaveCount);
			attendanceData.setMarked(marked);
			attendanceData.setMarked_percent(markedPercentage);
			attendanceData.setUnmarked(unmarked);
			attendanceData.setUnmarked_percent(unmarkedPercentage);
			attendanceData.setLeave_percent(leavePercentage);
			if (attendenceLst.get(i) instanceof AttendanceByAccount) {
				AttendanceByAccount atn = (AttendanceByAccount) attendenceLst.get(i);
				attendanceData.setAccountName(atn.getAccountName());
			} else {
				AttendanceByLocation atn = (AttendanceByLocation) attendenceLst.get(i);
				attendanceData.setLocationName(atn.getClient_location());
			}
			attendanceDataList.add(attendanceData);
		}

		if (attendenceLst.get(1) instanceof AttendanceByLocation) {
			AttendanceData attendanceData = new AttendanceData();
			attendanceData.setTotal(overAllTotal);
			attendanceData.setMarked(overAllMarked);
			attendanceData.setUnmarked(overAllUnMarked);
			attendanceData.setLeave(overAllLeave);
			attendanceData.setLocationName("ORGANISATION WIDE");
			attendanceDataList.add(0, attendanceData);
		}
		System.out.println("attendanceDataList=" + attendanceDataList);
		return attendanceDataList;

	}
}
