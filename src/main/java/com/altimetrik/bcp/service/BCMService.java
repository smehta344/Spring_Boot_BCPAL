package com.altimetrik.bcp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.bcp.dao.AttendanceRepo;
import com.altimetrik.bcp.dao.DailyStatusRepo;
import com.altimetrik.bcp.dao.LeaderRepo;
import com.altimetrik.bcp.dao.ProjectAssocRepo;
import com.altimetrik.bcp.entity.AttendanceStatus;
import com.altimetrik.bcp.entity.DailyStatus;
import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;
import com.altimetrik.bcp.entity.Project;
import com.altimetrik.bcp.model.AttendanceByAccount;
import com.altimetrik.bcp.model.AttendanceByLocation;
import com.altimetrik.bcp.model.AttendanceCommon;
import com.altimetrik.bcp.model.AttendanceData;
import com.altimetrik.bcp.model.AttendanceType;
import com.altimetrik.bcp.model.PlanDetailFormData;
import com.altimetrik.bcp.util.AppConstants;
import com.altimetrik.bcp.util.BcpUtils;

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
		List<ProjLocLeaderAssoc> assocList = projecAssocRepo.findAll();
		List<Project> projList = new ArrayList<Project>();
		for(ProjLocLeaderAssoc projAssoc:assocList){
			if(projAssoc.getAccount().getId() == accountId){
				projList.add(projAssoc.getProject());
			}
		}
		return projList;
	}
	
	public Map<String, AttendanceData> getAttendanceByAccount(String attdTypeValue, String attdType, Date fromDate ){
		java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(fromDate);
		List<AttendanceByAccount> attendanceByAccountList = new ArrayList<>();
		List<AttendanceData> attendanceDataList = new ArrayList<>();
		List<AttendanceStatus> attendanceStatusList = new ArrayList<>();
		if(attdTypeValue.equals("all") && attdType.equals("all")){
			attendanceByAccountList = attendenceRepo.getAttendByAllAccounts(dateString);
			List<AttendanceData> attnPercentageData = calculatePercentage(attendanceByAccountList);
			Map<String,AttendanceData> finalMap = new TreeMap<>();
			for(AttendanceData data : attnPercentageData){
				if(data.getAccountName() != null)
					finalMap.put(data.getAccountName(), data);
			}
			return finalMap;
		} else if(attdTypeValue.equals("all") && !attdType.equals("all")){
			attendanceByAccountList = attendenceRepo.getAttendByAllAccountsAndAttdStatus(dateString);
			attendanceStatusList = attendenceRepo.getAttendanceStatusByAttendanceStatusAndAttendanceDate(attdType,fromDate);
			attendanceDataList = calculatePercentage(attendanceByAccountList);
			
			Map<String,AttendanceData> finalMap = new TreeMap<>();
			for(AttendanceData data : attendanceDataList){
				if(data.getAccountName() != null){
					List<AttendanceStatus> datas = new ArrayList<>();
					for(AttendanceStatus status : attendanceStatusList){
						if(status.getAccountName()!= null && 
								status.getAccountName().equalsIgnoreCase(data.getAccountName())){
							datas.add(status);
						}
					}
					data.setEmployeeDetails(datas);
					finalMap.put(data.getAccountName(), data);
				}
			}
			return finalMap;
		} else if(!attdTypeValue.equals("all") && attdType.equals("all")) {
			AttendanceByAccount attendanceByAccount = attendenceRepo.getAttendByParticularAccount(attdTypeValue,dateString);
			attendanceStatusList = attendenceRepo.getAttendanceStatusByAccountNameAndAttendanceDate(attdTypeValue,fromDate);
			//List<AttendanceStatus> attendanceStatusByAccountName = attendenceRepo.getAttendanceStatusByAccountNameAndAttendanceStatus(attdTypeValue,"marked");
			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByAccount);
			attnPercentageData.setEmployeeDetails(attendanceStatusList);
			Map<String,AttendanceData> finalMap = new TreeMap<>();
			if(attnPercentageData.getAccountName() != null){
				finalMap.put(attnPercentageData.getAccountName(), attnPercentageData);
			}
			return finalMap;
		} else if(!attdTypeValue.equals("all") && !attdType.equals("all")){
			AttendanceByAccount attendanceByAccount = attendenceRepo.getAttendByParticularAccount(attdTypeValue,dateString);
			attendanceStatusList = attendenceRepo.getAttendanceStatusByAccountNameAndAttendanceStatusAndAttendanceDate(attdTypeValue,attdType,fromDate);
			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByAccount);
			attnPercentageData.setEmployeeDetails(attendanceStatusList);
			Map<String,AttendanceData> finalMap = new TreeMap<>();
			if(attnPercentageData.getAccountName() != null){
				finalMap.put(attnPercentageData.getAccountName(), attnPercentageData);
			}
			return finalMap;
		}
		return null;
	}
	
	public Map<String,List<String>> getAttendancePercent(String wiseType, String attdTypeValue, String attdType, Date fromDate ) throws ParseException{
		Map<Date,Map<String,Double>> finalMap = new TreeMap<>();
		List<String> keyData = new ArrayList<>();
		for(int day = 0; day < AppConstants.NO_OF_DAYS_TO_FETCH_ATTENDANCE_PERCENT ; day++){
			Date date = BcpUtils.subtractDays(fromDate, day);
			Map<String, AttendanceData> attendanceByAccount = null;
			Map<String,Double> attendancePercentForParticularDate = new TreeMap<>();
			
			if(AttendanceType.ACCOUNT == AttendanceType.valueOf(wiseType)){
				attendanceByAccount = getAttendanceByAccount(attdTypeValue, attdType, date);
				if(attendanceByAccount.isEmpty()){
					if(!attdTypeValue.equals("all")){
						keyData.add(attdTypeValue);
					} else {
						keyData = getAccountNames();
					}
					
					for(String key : keyData ){
						attendancePercentForParticularDate.put(key.toUpperCase(), (double) 0);
					}
				}
			} else {
				attendanceByAccount = getAttendanceByLocation(attdTypeValue, attdType, date);
				if(attendanceByAccount.isEmpty()){
					if(!attdTypeValue.equals("all")){
						keyData.add(attdTypeValue);
					} else {
						keyData = getClientLocations();
						keyData.add("ORGANISATION WIDE");
					}
					
					for(String key : keyData ){
						attendancePercentForParticularDate.put(key.toUpperCase(), (double)0);
					}
					
				}
			}
			
			attendanceByAccount.forEach((k,v)->{
				if(attdType.equals("Marked")){
					attendancePercentForParticularDate.put(k.toUpperCase(), v.getMarked_percent());
				} else if(attdType.equals("Not Marked")){
					attendancePercentForParticularDate.put(k.toUpperCase(), v.getUnmarked_percent());
				} else if (attdType.equals("Leave")){
					attendancePercentForParticularDate.put(k.toUpperCase(), v.getLeave_percent());
				} else if (attdType.equals("Leave - Approval Pending")){
					attendancePercentForParticularDate.put(k.toUpperCase(), v.getLeave_app_pend_percent());
				} else {
					attendancePercentForParticularDate.put(k.toUpperCase(), v.getMarked_percent());
				}
			});
			finalMap.put(BcpUtils.dateFormatterForAttdPercent(date),attendancePercentForParticularDate);
		}
		return formJsonDataForPercentageTable(finalMap, keyData);
	}
	
	private Map<String,List<String>> formJsonDataForPercentageTable(Map<Date,Map<String,Double>> finalMap,List<String> keyData){
		Map<String,List<String>> dd= new LinkedHashMap<>();
		List<String> dateList = new ArrayList<>();
		for (Map.Entry<Date,Map<String,Double>> entry : finalMap.entrySet()){
			dateList.add(new SimpleDateFormat("dd/MMM/yyyy").format(entry.getKey()));
		}
		dd.put("", dateList);
		for(String key : keyData){
			List<String> list = new ArrayList<>();
			for (Map.Entry<Date,Map<String,Double>> entry : finalMap.entrySet()){
	            for (Map.Entry<String,Double> data : entry.getValue().entrySet()){
	            	if(data.getKey().equals(key.toUpperCase())){
	            		list.add(Double.toString(data.getValue()));
	            	}
	            }
			}
			dd.put(key.toUpperCase(),list);
		}
		return dd;
	}
	
	
	public Map<String, AttendanceData> getAttendanceByLocation(String attdTypeValue, String attdType,Date fromDate){
		java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(fromDate);
		
		if(attdTypeValue.equals("all") && attdType.equals("all")){
			List<AttendanceByLocation> attendanceList = attendenceRepo.getAttendByLocation(dateString);
			List<AttendanceData> attnPercentageData = calculatePercentage(attendanceList);
			Map<String,AttendanceData> finalMap = new HashMap<>();
			for(AttendanceData data : attnPercentageData){
				if(data.getLocationName() != null)
					finalMap.put(data.getLocationName(), data);
			}
			return finalMap;
		} else if(attdTypeValue.equals("all") && !attdType.equals("all")){
			List<AttendanceByLocation> attendanceByAccountList = attendenceRepo.getAttendByAllLocationsAndAttdStatus(dateString);
			List<AttendanceStatus> attendanceStatusList = attendenceRepo.getAttendanceStatusByAttendanceStatusAndAttendanceDate(attdType,fromDate);
			List<AttendanceData> attendanceDataList = calculatePercentage(attendanceByAccountList);
			
			Map<String,AttendanceData> finalMap = new TreeMap<>();
			for(AttendanceData data : attendanceDataList){
				if(data.getLocationName() != null){
					List<AttendanceStatus> datas = new ArrayList<>();
					for(AttendanceStatus status : attendanceStatusList){
						if(status.getClinetLocation()!= null && 
								status.getClinetLocation().equalsIgnoreCase(data.getLocationName())){
							datas.add(status);
						}
					}
					data.setEmployeeDetails(datas);
					finalMap.put(data.getLocationName(), data);
				}
			}
			return finalMap;
		} else if(!attdTypeValue.equals("all") && attdType.equals("all")) {
			AttendanceByLocation attendanceByLocation = attendenceRepo.getAttendByParticularLocation(attdTypeValue,dateString);
			List<AttendanceStatus> attendanceStatusList = attendenceRepo.getAttendanceStatusByClinetLocationAndAttendanceDate(attdTypeValue,fromDate);
			//List<AttendanceStatus> attendanceStatusByAccountName = attendenceRepo.getAttendanceStatusByAccountNameAndAttendanceStatus(attdTypeValue,"marked");
			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByLocation);
			attnPercentageData.setEmployeeDetails(attendanceStatusList);
			Map<String,AttendanceData> finalMap = new TreeMap<>();
			if(attnPercentageData.getLocationName() != null){
				finalMap.put(attnPercentageData.getLocationName(), attnPercentageData);
			}
			return finalMap;
		} else if(!attdTypeValue.equals("all") && !attdType.equals("all")){
			AttendanceByLocation attendanceByLocation = attendenceRepo.getAttendByParticularLocation(attdTypeValue,dateString);
			List<AttendanceStatus> attendanceStatusList = attendenceRepo.getAttendanceStatusByClinetLocationAndAttendanceStatusAndAttendanceDate(attdTypeValue,attdType,fromDate);
			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByLocation);
			attnPercentageData.setEmployeeDetails(attendanceStatusList);
			Map<String,AttendanceData> finalMap = new TreeMap<>();
			if(attnPercentageData.getLocationName() != null){
				finalMap.put(attnPercentageData.getLocationName(), attnPercentageData);
			}
			return finalMap;
		}
		return null;
		
	}
	
	private AttendanceData calculatePercentageParticularAcc(AttendanceCommon attendanceByAccount){
		AttendanceData attendanceData = new AttendanceData();
		if(attendanceByAccount != null){
			double total = attendanceByAccount.getTotal();
			double marked = attendanceByAccount.getMarked();
			double unmarked = attendanceByAccount.getUnmarked();
			double leaveCount = attendanceByAccount.getLeave_count();
			double leaveApprovalPending = attendanceByAccount.getLeave_app_pend();
			double markedPercentage = 0;
			double unmarkedPercentage =0;
			double leavePercentage = 0;
			double leaveAppPendPercentage = 0;
			if(marked > 0)
				markedPercentage = (marked * 100 / total);
			if(unmarked > 0)
				unmarkedPercentage = (unmarked * 100 / total);
			if(leaveCount > 0)
				leavePercentage = (leaveCount * 100 / total);
			if(leaveApprovalPending > 0)
				leaveAppPendPercentage = (leaveApprovalPending * 100 / total);
			
			
			attendanceData.setTotal(BcpUtils.roundDoubleValue(total));
			attendanceData.setLeave(BcpUtils.roundDoubleValue(leaveCount));
			attendanceData.setMarked(BcpUtils.roundDoubleValue(marked));
			attendanceData.setLeaveAppPending(BcpUtils.roundDoubleValue(leaveApprovalPending));
			attendanceData.setMarked_percent(BcpUtils.roundDoubleValue(markedPercentage));
			attendanceData.setUnmarked(BcpUtils.roundDoubleValue(unmarked));
			attendanceData.setUnmarked_percent(BcpUtils.roundDoubleValue(unmarkedPercentage));
			attendanceData.setLeave_percent(BcpUtils.roundDoubleValue(leavePercentage));
			attendanceData.setLeave_app_pend_percent(BcpUtils.roundDoubleValue(leaveAppPendPercentage));
			if(attendanceByAccount instanceof AttendanceByAccount){
				AttendanceByAccount atn = (AttendanceByAccount) attendanceByAccount;
				attendanceData.setAccountName(atn.getAccountName());
			}
			else{
				AttendanceByLocation atn = (AttendanceByLocation) attendanceByAccount;
				attendanceData.setLocationName(atn.getClient_location());
			}
		}
		return attendanceData;
	}
	public List<AttendanceData> calculatePercentage(List<? extends AttendanceCommon> attendenceLst){
		double total;
		double marked;
		double unmarked;
		double leaveCount;
		double leaveApprovalPending;
		double markedPercentage = 0;
		double unmarkedPercentage=0;
		double leavePercentage=0;
		double leaveAppPendPercentage=0;
		
		double overAllTotal = 0;
		double overAllMarked = 0;
		double overAllUnMarked = 0;
		double overAllLeave = 0;
		double overAllLeaveAppPending = 0;
		double overAllMarkedPercentage = 0;
		double overAllUnmarkedPercentage=0;
		double overAllLeavePercentage=0;
		double overAllLeaveAppPendPercentage=0;
		List<AttendanceData> attendanceDataList = new ArrayList<AttendanceData>();
		for(int i=0; i<attendenceLst.size(); i++ ){
				total = attendenceLst.get(i).getTotal();
				marked = attendenceLst.get(i).getMarked();
				unmarked = attendenceLst.get(i).getUnmarked();
				leaveCount = attendenceLst.get(i).getLeave_count();
				leaveApprovalPending = attendenceLst.get(i).getLeave_app_pend();
				if(marked > 0)
					markedPercentage = (marked * 100 / total);
				else 
					markedPercentage = 0;
				if(unmarked > 0)
					unmarkedPercentage = (unmarked * 100 / total);
				else 
					unmarkedPercentage = 0;
				if(leaveCount > 0)
					leavePercentage = (leaveCount * 100 / total);
				else 
					leavePercentage = 0;
				if(leaveApprovalPending > 0)
					leaveAppPendPercentage = (leaveApprovalPending * 100 / total);
				else 
					leaveAppPendPercentage = 0;
				
				overAllTotal = overAllTotal+total;
				overAllMarked = overAllMarked+marked;
				overAllUnMarked = overAllUnMarked+unmarked;
				overAllLeave = overAllLeave+leaveCount;
				overAllLeaveAppPending = overAllLeaveAppPending+leaveApprovalPending;
				AttendanceData attendanceData = new AttendanceData();
				attendanceData.setTotal(BcpUtils.roundDoubleValue(total));
				attendanceData.setLeave(BcpUtils.roundDoubleValue(leaveCount));
				attendanceData.setLeaveAppPending(BcpUtils.roundDoubleValue(leaveApprovalPending));
				attendanceData.setMarked(BcpUtils.roundDoubleValue(marked));
				attendanceData.setMarked_percent(BcpUtils.roundDoubleValue(markedPercentage));
				attendanceData.setUnmarked(BcpUtils.roundDoubleValue(unmarked));
				attendanceData.setUnmarked_percent(BcpUtils.roundDoubleValue(unmarkedPercentage));
				attendanceData.setLeave_percent(BcpUtils.roundDoubleValue(leavePercentage));
				attendanceData.setLeave_app_pend_percent(BcpUtils.roundDoubleValue(leaveAppPendPercentage));
				if(attendenceLst.get(i) instanceof AttendanceByAccount){
					AttendanceByAccount atn = (AttendanceByAccount) attendenceLst.get(i);
					attendanceData.setAccountName(atn.getAccountName());
				}
				else{
					AttendanceByLocation atn = (AttendanceByLocation) attendenceLst.get(i);
					attendanceData.setLocationName(atn.getClient_location());
				}
				attendanceDataList.add(attendanceData);
		}
		
		if(!attendenceLst.isEmpty() && attendenceLst.get(0) instanceof AttendanceByLocation){
			if(overAllMarked > 0)
				overAllMarkedPercentage = (overAllMarked * 100 / overAllTotal);
			if(overAllUnMarked > 0)
				overAllUnmarkedPercentage = (overAllUnMarked * 100 / overAllTotal);
			if(overAllLeave > 0)
				overAllLeavePercentage = (overAllLeave * 100 / overAllTotal);
			if(overAllLeaveAppPending > 0)
				overAllLeaveAppPendPercentage = (overAllLeaveAppPending * 100 / overAllTotal);
			AttendanceData attendanceData = new AttendanceData();
			attendanceData.setTotal(BcpUtils.roundDoubleValue(overAllTotal));
			attendanceData.setMarked(BcpUtils.roundDoubleValue(overAllMarked));
			attendanceData.setUnmarked(BcpUtils.roundDoubleValue(overAllUnMarked));
			attendanceData.setLeave(BcpUtils.roundDoubleValue(overAllLeave));
			attendanceData.setLeaveAppPending(BcpUtils.roundDoubleValue(overAllLeaveAppPending));
			attendanceData.setMarked_percent(BcpUtils.roundDoubleValue(overAllMarkedPercentage));
			attendanceData.setUnmarked_percent(BcpUtils.roundDoubleValue(overAllUnmarkedPercentage));
			attendanceData.setLeave_percent(BcpUtils.roundDoubleValue(overAllLeavePercentage));
			attendanceData.setLeave_app_pend_percent(BcpUtils.roundDoubleValue(overAllLeaveAppPendPercentage));
			attendanceData.setLocationName("ORGANISATION WIDE");
			attendanceDataList.add(0,attendanceData);
		}
		return attendanceDataList;
		
	}
	
	public List<String> getAccountNames(){
		return attendenceRepo.findDistinctAccountName();
	}
	
	public List<String> getClientLocations(){
		return attendenceRepo.findDistinctClientLocation();
	}
	
}
