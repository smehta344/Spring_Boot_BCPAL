package com.altimetrik.bcp.service;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.bcp.dao.AccLocLeaderAssocrepo;
import com.altimetrik.bcp.dao.AccountRepo;
import com.altimetrik.bcp.dao.AttendanceRepo;
import com.altimetrik.bcp.dao.DailyStatusRepo;
import com.altimetrik.bcp.dao.LeaderRepo;
import com.altimetrik.bcp.dao.ProjectRepo;
import com.altimetrik.bcp.dao.TodaySummaryRepo;
import com.altimetrik.bcp.entity.AccLocLeaderAssoc;
import com.altimetrik.bcp.entity.Account;
import com.altimetrik.bcp.entity.AttendanceStatus;
import com.altimetrik.bcp.entity.DailyStatus;
import com.altimetrik.bcp.entity.Project;
import com.altimetrik.bcp.entity.TodaySummary;
import com.altimetrik.bcp.model.AttendanceByAccount;
import com.altimetrik.bcp.model.AttendanceByLocation;
import com.altimetrik.bcp.model.AttendanceCommon;
import com.altimetrik.bcp.model.AttendanceData;
import com.altimetrik.bcp.model.AttendanceType;
import com.altimetrik.bcp.model.DeliveryInput;
import com.altimetrik.bcp.model.DeliverySummary;
import com.altimetrik.bcp.model.PlanDetailFormData;
import com.altimetrik.bcp.model.TodaySummaryDto;
import com.altimetrik.bcp.util.AppConstants;
import com.altimetrik.bcp.util.BcpUtils;

@Service
public class BCMService {

	@Autowired
	ProjectRepo projecRepo;

	@Autowired
	LeaderRepo leaderRepo;

	@Autowired
	AttendanceRepo attendenceRepo;

	@Autowired
	AccLocLeaderAssocrepo assoRepo;

	@Autowired
	DailyStatusRepo dailyStatusRepo;

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	TodaySummaryRepo todaySummaryRepo;

	public void createDilyStatus(PlanDetailFormData formaData) {
		DailyStatus statusObj = createStatusObj(formaData);
		dailyStatusRepo.save(statusObj);
	}

	public DailyStatus createStatusObj(PlanDetailFormData formData) {
		DailyStatus statusObject = new DailyStatus();
		statusObject.setDate(formData.getDate());
		statusObject.setChallenges(formData.getDeliveryChallenge());
		statusObject.setLocationId(formData.getLocationId());
		statusObject.setProjectId(projecRepo.findById(formData.getProjectId()).get());

		// TODO Need to add below attributes with correct format
		statusObject.setCreatedBy("Test");
		statusObject.setCreatedTime(new Date());
		statusObject.setUpdatedBy("TEST");
		statusObject.setUpdatedTime(new Date());
		statusObject.setUpdates("test");
		statusObject.setStatus(formData.getStatus());

		statusObject.setDeliverableOfDay(formData.getKeyDeliverable());
		statusObject.setMilestone(formData.getMilestone());
		statusObject.setWfhChallenge(formData.getWfhChallenge());
		statusObject.setWfhMitigationPlan(formData.getWfhMitigationPlan());
		statusObject.setMitigationPlan(formData.getDeliveryMitigationPlan());
		statusObject.setHiringUpdate(formData.getHiringUpdate());
		statusObject.setTeamLogged(formData.getTeamlogedCount());
		return statusObject;
	}

	public DeliveryInput getLocationAndLeader(int projectId, int accountId) {
		DeliveryInput deliveryInput = new DeliveryInput();
		Project project = projecRepo.findById(projectId).get();
		int locationId = project.getLocation().getId();
		AccLocLeaderAssoc associationData = assoRepo.findTopByAccountIdAndLocationId(accountId, locationId);
		deliveryInput.setLocation(associationData.getLocation());
		deliveryInput.setLeader(associationData.getLeader());
		deliveryInput.setTeamSize(project.getTeamSize());
		return deliveryInput;
	}

	public List<Project> getProjectById(int accountId) {
		return projecRepo.findByAccountId(accountId);
	}

	public PlanDetailFormData getHistoryData(Date fromDate, int projectId) {
		PlanDetailFormData planData = new PlanDetailFormData();
		DailyStatus dailyStatus = dailyStatusRepo.findByDateAndProject(fromDate, projecRepo.findById(projectId).get());
		if (dailyStatus != null) {
			planData.setDeliveryChallenge(dailyStatus.getChallenges());
			planData.setProjectName(dailyStatus.getProject().getName());
			planData.setMilestone(dailyStatus.getMilestone());
			planData.setDeliveryMitigationPlan(dailyStatus.getMitigationPlan());
			planData.setWfhChallenge(dailyStatus.getWfhChallenge());
			planData.setWfhMitigationPlan(dailyStatus.getWfhMitigationPlan());
			planData.setKeyDeliverable(dailyStatus.getDeliverableOfDay());
			planData.setHiringUpdate(dailyStatus.getHiringUpdate());
			planData.setTeamlogedCount(dailyStatus.getTeamLogged());
		}
		return planData;
	}

	public Map<String, AttendanceData> getAttendanceByAccount(String attdTypeValue, String billingStatus,
			String attdType, Date fromDate) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(fromDate);

		List<AttendanceByAccount> attendanceByAccountList = new ArrayList<>();
		List<AttendanceData> attendanceDataList = new ArrayList<>();
		List<AttendanceStatus> attendanceStatusList = new ArrayList<>();
		if (attdTypeValue.equals(AppConstants.ALL) && attdType.equals(AppConstants.ALL)
				&& billingStatus.equals(AppConstants.ALL)) {
			attendanceByAccountList = attendenceRepo.getAttendByAllAccounts(dateString);
			List<AttendanceData> attnPercentageData = calculatePercentage(attendanceByAccountList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			for (AttendanceData data : attnPercentageData) {
				if (data.getAccountName() != null)
					finalMap.put(data.getAccountName(), data);
			}
			return finalMap;
		} else if (attdTypeValue.equals(AppConstants.ALL) && !attdType.equals(AppConstants.ALL)
				&& billingStatus.equals(AppConstants.ALL)) {
			attendanceByAccountList = attendenceRepo.getAttendByAllAccountsAndAttdStatus(dateString);
			if (!attdType.equals(AppConstants.LEAVE)) {
				attendanceStatusList = attendenceRepo.getAttendanceStatusByAttendanceStatusAndAttendanceDate(attdType,
						fromDate);
			} else {
				attendanceStatusList = attendenceRepo.getAttendanceStatusByAttendanceStatusInAndAttendanceDate(
						BcpUtils.getLeaveDbValues(), fromDate);
			}
			attendanceDataList = calculatePercentage(attendanceByAccountList);

			Map<String, AttendanceData> finalMap = new TreeMap<>();
			for (AttendanceData data : attendanceDataList) {
				if (data.getAccountName() != null) {
					List<AttendanceStatus> datas = new ArrayList<>();
					for (AttendanceStatus status : attendanceStatusList) {
						if (status.getAccountName() != null
								&& status.getAccountName().equalsIgnoreCase(data.getAccountName())) {
							datas.add(status);
						}
					}
					data.setEmployeeDetails(datas);
					finalMap.put(data.getAccountName(), data);
				}
			}
			return finalMap;
		} else if (!attdTypeValue.equals(AppConstants.ALL) && attdType.equals(AppConstants.ALL)
				&& billingStatus.equals(AppConstants.ALL)) {
			AttendanceByAccount attendanceByAccount = attendenceRepo.getAttendByParticularAccount(attdTypeValue,
					dateString);
			attendanceStatusList = attendenceRepo.getAttendanceStatusByAccountNameAndAttendanceDate(attdTypeValue,
					fromDate);
			// List<AttendanceStatus> attendanceStatusByAccountName =
			// attendenceRepo.getAttendanceStatusByAccountNameAndAttendanceStatus(attdTypeValue,"marked");
			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByAccount);
			attnPercentageData.setEmployeeDetails(attendanceStatusList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			if (attnPercentageData.getAccountName() != null) {
				finalMap.put(attnPercentageData.getAccountName(), attnPercentageData);
			}
			return finalMap;
		} else if (!attdTypeValue.equals(AppConstants.ALL) && !attdType.equals(AppConstants.ALL)
				&& billingStatus.equals(AppConstants.ALL)) {
			AttendanceByAccount attendanceByAccount = attendenceRepo.getAttendByParticularAccount(attdTypeValue,
					dateString);

			if (!attdType.equals(AppConstants.LEAVE)) {
				attendanceStatusList = attendenceRepo
						.getAttendanceStatusByAccountNameAndAttendanceStatusAndAttendanceDate(attdTypeValue, attdType,
								fromDate);
			} else {
				attendanceStatusList = attendenceRepo
						.getAttendanceStatusByAccountNameAndAttendanceStatusInAndAttendanceDate(attdTypeValue,
								BcpUtils.getLeaveDbValues(), fromDate);
			}

			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByAccount);
			attnPercentageData.setEmployeeDetails(attendanceStatusList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			if (attnPercentageData.getAccountName() != null) {
				finalMap.put(attnPercentageData.getAccountName(), attnPercentageData);
			}
			return finalMap;

		} else if (attdTypeValue.equals(AppConstants.ALL) && attdType.equals(AppConstants.ALL)
				&& !billingStatus.equals(AppConstants.ALL)) {
			attendanceByAccountList = attendenceRepo.getAttendByAllAccountsDateAndBillingStatus(billingStatus,
					dateString);
			List<AttendanceData> attnPercentageData = calculatePercentage(attendanceByAccountList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			for (AttendanceData data : attnPercentageData) {
				if (data.getAccountName() != null)
					finalMap.put(data.getAccountName(), data);
			}
			return finalMap;
		} else if (attdTypeValue.equals(AppConstants.ALL) && !attdType.equals(AppConstants.ALL)
				&& !billingStatus.equals(AppConstants.ALL)) {
			attendanceByAccountList = attendenceRepo.getAttendByAllAccountsDateAndBillingStatus(billingStatus,
					dateString);
			if (!attdType.equals(AppConstants.LEAVE)) {
				attendanceStatusList = attendenceRepo.getAttendanceStatusByAttendanceStatusAndCategoryAndAttendanceDate(
						"Not Marked", billingStatus, fromDate);
			} else {
				attendanceStatusList = attendenceRepo
						.getAttendanceStatusByAttendanceStatusInAndCategoryAndAttendanceDate(
								BcpUtils.getLeaveDbValues(), billingStatus, fromDate);
			}

			attendanceDataList = calculatePercentage(attendanceByAccountList);

			Map<String, AttendanceData> finalMap = new TreeMap<>();
			for (AttendanceData data : attendanceDataList) {
				if (data.getAccountName() != null) {
					List<AttendanceStatus> datas = new ArrayList<>();
					Set<String> set = new HashSet<>();
					for (AttendanceStatus status : attendanceStatusList) {
						if (status.getAccountName() != null
								&& status.getAccountName().equalsIgnoreCase(data.getAccountName())
								&& !set.contains(status.getEmployeeId())) {
							set.add(status.getEmployeeId());
							datas.add(status);
						}
					}
					data.setEmployeeDetails(datas);
					finalMap.put(data.getAccountName(), data);
				}
			}
			return finalMap;
		} else if (!attdTypeValue.equals(AppConstants.ALL) && attdType.equals(AppConstants.ALL)
				&& !billingStatus.equals(AppConstants.ALL)) {
			AttendanceByAccount attendanceByAccount = attendenceRepo
					.getAttendByParticularAccountWithCategory(attdTypeValue, billingStatus, dateString);
			attendanceStatusList = attendenceRepo.getAttendanceStatusByAccountNameAndCategoryAndAttendanceDate(
					attdTypeValue, billingStatus, fromDate);
			// List<AttendanceStatus> attendanceStatusByAccountName =
			// attendenceRepo.getAttendanceStatusByAccountNameAndAttendanceStatus(attdTypeValue,"marked");
			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByAccount);
			attnPercentageData.setEmployeeDetails(attendanceStatusList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			if (attnPercentageData.getAccountName() != null) {
				finalMap.put(attnPercentageData.getAccountName(), attnPercentageData);
			}
			return finalMap;
		}

		else if (!attdTypeValue.equals(AppConstants.ALL) && !attdType.equals(AppConstants.ALL)
				&& !billingStatus.equals(AppConstants.ALL)) {
			AttendanceByAccount attendanceByAccount = attendenceRepo
					.getAttendByParticularAccountWithCategory(attdTypeValue, billingStatus, dateString);
			if (!attdType.equals(AppConstants.LEAVE)) {
				attendanceStatusList = attendenceRepo
						.getAttendanceStatusByAccountNameAndAttendanceStatusAndCategoryAndAttendanceDate(attdTypeValue,
								"Not Marked", billingStatus, fromDate);
			} else {
				attendanceStatusList = attendenceRepo
						.getAttendanceStatusByAccountNameAndAttendanceStatusInAndCategoryAndAttendanceDate(
								attdTypeValue, BcpUtils.getLeaveDbValues(), billingStatus, fromDate);
			}
			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByAccount);
			List<AttendanceStatus> uniqueList = attendanceStatusList.stream().collect(collectingAndThen(
					toCollection(() -> new TreeSet<>(comparing(AttendanceStatus::getEmployeeId))), ArrayList::new));
			attnPercentageData.setEmployeeDetails(uniqueList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			if (attnPercentageData.getAccountName() != null) {
				finalMap.put(attnPercentageData.getAccountName(), attnPercentageData);
			}
			return finalMap;
		}
		return null;
	}

	public Map<String, List<String>> getAttendancePercent(String wiseType, String attdTypeValue, String billingStatus,
			String attdType, Date fromDate) throws ParseException {
		Map<Date, Map<String, Double>> finalMap = new TreeMap<>();
		List<String> keyData = new ArrayList<>();
		for (int day = 0; day < AppConstants.NO_OF_DAYS_TO_FETCH_ATTENDANCE_PERCENT; day++) {
			Date date = BcpUtils.subtractDays(fromDate, day);
			Map<String, AttendanceData> attendanceByAccount = null;
			Map<String, Double> attendancePercentForParticularDate = new TreeMap<>();

			if (AttendanceType.ACCOUNT == AttendanceType.valueOf(wiseType)) {
				attendanceByAccount = getAttendanceByAccount(attdTypeValue, billingStatus, attdType, date);
				if (attendanceByAccount.isEmpty()) {
					if (!attdTypeValue.equals(AppConstants.ALL)) {
						keyData.add(attdTypeValue);
					} else {
						keyData = getAccountNames();
					}

					for (String key : keyData) {
						attendancePercentForParticularDate.put(key.toUpperCase(), (double) 0);
					}
				}
			} else {
				attendanceByAccount = getAttendanceByLocation(attdTypeValue, billingStatus, attdType, date);
				if (attendanceByAccount.isEmpty()) {
					if (!attdTypeValue.equals(AppConstants.ALL)) {
						keyData.add(attdTypeValue);
					} else {
						if (billingStatus.equals(AppConstants.ALL)) {
							keyData = getClientLocations();
						} else {
							keyData = getClientLocationsByCategory(billingStatus);
						}
						keyData.add(0, AppConstants.ORGANISATION_WIDE);
					}

					for (String key : keyData) {
						attendancePercentForParticularDate.put(key.toUpperCase(), (double) 0);
					}

				}
			}

			attendanceByAccount.forEach((k, v) -> {
				if (attdType.equals("Marked")) {
					attendancePercentForParticularDate.put(k.toUpperCase(), v.getMarked_percent());
				} else if (attdType.equals("Not Marked")) {
					attendancePercentForParticularDate.put(k.toUpperCase(), v.getUnmarked_percent());
				} else if (attdType.equals("Leave")) {
					attendancePercentForParticularDate.put(k.toUpperCase(), v.getLeave_percent());
				} else if (attdType.equals("Leave - Approval Pending")) {
					attendancePercentForParticularDate.put(k.toUpperCase(), v.getLeave_app_pend_percent());
				} else {
					attendancePercentForParticularDate.put(k.toUpperCase(), v.getMarked_percent());
				}
			});
			finalMap.put(BcpUtils.dateFormatterForAttdPercent(date), attendancePercentForParticularDate);
		}
		return formJsonDataForPercentageTable(finalMap, keyData);
	}

	private Map<String, List<String>> formJsonDataForPercentageTable(Map<Date, Map<String, Double>> finalMap,
			List<String> keyData) {
		Map<String, List<String>> dd = new LinkedHashMap<>();
		List<String> dateList = new ArrayList<>();
		for (Map.Entry<Date, Map<String, Double>> entry : finalMap.entrySet()) {
			dateList.add(new SimpleDateFormat("dd/MMM/yyyy").format(entry.getKey()));
		}
		dd.put("", dateList);
		for (String key : keyData) {
			List<String> list = new ArrayList<>();
			for (Map.Entry<Date, Map<String, Double>> entry : finalMap.entrySet()) {
				for (Map.Entry<String, Double> data : entry.getValue().entrySet()) {
					if (data.getKey().equals(key.toUpperCase())) {
						list.add(Double.toString(data.getValue()));
					}
				}
			}
			dd.put(key.toUpperCase(), list);
		}
		return dd;
	}

	public Map<String, AttendanceData> getAttendanceByLocation(String attdTypeValue, String billingStatus,
			String attdType, Date fromDate) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(fromDate);

		if (attdTypeValue.equals(AppConstants.ALL) && attdType.equals(AppConstants.ALL)
				&& billingStatus.equals(AppConstants.ALL)) {
			List<AttendanceByLocation> attendanceList = attendenceRepo.getAttendByLocation(dateString);
			List<AttendanceData> attnPercentageData = calculatePercentage(attendanceList);
			Map<String, AttendanceData> finalMap = new HashMap<>();
			for (AttendanceData data : attnPercentageData) {
				if (data.getLocationName() != null)
					finalMap.put(data.getLocationName(), data);
			}
			return finalMap;
		} else if (attdTypeValue.equals(AppConstants.ALL) && !attdType.equals(AppConstants.ALL)
				&& billingStatus.equals(AppConstants.ALL)) {
			List<AttendanceByLocation> attendanceByAccountList = attendenceRepo
					.getAttendByAllLocationsAndAttdStatus(dateString);
			List<AttendanceStatus> attendanceStatusList = new ArrayList<>();
			if (!attdType.equals(AppConstants.LEAVE)) {
				attendanceStatusList = attendenceRepo.getAttendanceStatusByAttendanceStatusAndAttendanceDate(attdType,
						fromDate);
			} else {
				attendanceStatusList = attendenceRepo.getAttendanceStatusByAttendanceStatusInAndAttendanceDate(
						BcpUtils.getLeaveDbValues(), fromDate);
			}
			List<AttendanceData> attendanceDataList = calculatePercentage(attendanceByAccountList);

			Map<String, AttendanceData> finalMap = new TreeMap<>();
			for (AttendanceData data : attendanceDataList) {
				if (data.getLocationName() != null) {
					List<AttendanceStatus> datas = new ArrayList<>();
					for (AttendanceStatus status : attendanceStatusList) {
						if (status.getClinetLocation() != null
								&& status.getClinetLocation().equalsIgnoreCase(data.getLocationName())) {
							datas.add(status);
						}
					}
					data.setEmployeeDetails(datas);
					finalMap.put(data.getLocationName(), data);
				}
			}
			return finalMap;
		} else if (!attdTypeValue.equals(AppConstants.ALL) && attdType.equals(AppConstants.ALL)
				&& billingStatus.equals(AppConstants.ALL)) {
			AttendanceByLocation attendanceByLocation = attendenceRepo.getAttendByParticularLocation(attdTypeValue,
					dateString);
			List<AttendanceStatus> attendanceStatusList = attendenceRepo
					.getAttendanceStatusByClinetLocationAndAttendanceDate(attdTypeValue, fromDate);
			// List<AttendanceStatus> attendanceStatusByAccountName =
			// attendenceRepo.getAttendanceStatusByAccountNameAndAttendanceStatus(attdTypeValue,"marked");
			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByLocation);
			attnPercentageData.setEmployeeDetails(attendanceStatusList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			if (attnPercentageData.getLocationName() != null) {
				finalMap.put(attnPercentageData.getLocationName(), attnPercentageData);
			}
			return finalMap;
		} else if (!attdTypeValue.equals(AppConstants.ALL) && !attdType.equals(AppConstants.ALL)
				&& billingStatus.equals(AppConstants.ALL)) {
			AttendanceByLocation attendanceByLocation = attendenceRepo.getAttendByParticularLocation(attdTypeValue,
					dateString);
			List<AttendanceStatus> attendanceStatusList = new ArrayList<>();
			if (!attdType.equals(AppConstants.LEAVE)) {
				attendanceStatusList = attendenceRepo
						.getAttendanceStatusByClinetLocationAndAttendanceStatusAndAttendanceDate(attdTypeValue,
								attdType, fromDate);
			} else {
				attendanceStatusList = attendenceRepo
						.getAttendanceStatusByClinetLocationAndAttendanceStatusInAndAttendanceDate(attdTypeValue,
								BcpUtils.getLeaveDbValues(), fromDate);
			}
			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByLocation);
			attnPercentageData.setEmployeeDetails(attendanceStatusList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			if (attnPercentageData.getLocationName() != null) {
				finalMap.put(attnPercentageData.getLocationName(), attnPercentageData);
			}
			return finalMap;
		} else if (attdTypeValue.equals(AppConstants.ALL) && attdType.equals(AppConstants.ALL)
				&& !billingStatus.equals(AppConstants.ALL)) {
			List<AttendanceByLocation> attendanceList = attendenceRepo
					.getAttendByAllLocationsDateAndBillingStatus(billingStatus, dateString);
			System.out.println("size=" + attendanceList.size());
			List<AttendanceData> attnPercentageData = calculatePercentage(attendanceList);
			Map<String, AttendanceData> finalMap = new HashMap<>();
			for (AttendanceData data : attnPercentageData) {
				if (data.getLocationName() != null)
					finalMap.put(data.getLocationName(), data);
			}
			return finalMap;
		} else if (attdTypeValue.equals(AppConstants.ALL) && !attdType.equals(AppConstants.ALL)
				&& !billingStatus.equals(AppConstants.ALL)) {
			List<AttendanceByLocation> attendanceByAccountList = attendenceRepo
					.getAttendByAllLocationsDateAndBillingStatus(billingStatus, dateString);
			List<AttendanceStatus> attendanceStatusList = new ArrayList<>();
			if (!attdType.equals(AppConstants.LEAVE)) {
				attendanceStatusList = attendenceRepo.getAttendanceStatusByAttendanceStatusAndCategoryAndAttendanceDate(
						"Not Marked", billingStatus, fromDate);
			} else {
				attendanceStatusList = attendenceRepo
						.getAttendanceStatusByAttendanceStatusInAndCategoryAndAttendanceDate(
								BcpUtils.getLeaveDbValues(), billingStatus, fromDate);
			}
			List<AttendanceData> attendanceDataList = calculatePercentage(attendanceByAccountList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			for (AttendanceData data : attendanceDataList) {
				if (data.getLocationName() != null) {
					List<AttendanceStatus> datas = new ArrayList<>();
					Set<String> set = new HashSet<>();
					for (AttendanceStatus status : attendanceStatusList) {
						if (status.getClinetLocation() != null
								&& status.getClinetLocation().equalsIgnoreCase(data.getLocationName())
								&& !set.contains(status.getEmployeeId())) {
							set.add(status.getEmployeeId());
							datas.add(status);
						}
					}
					data.setEmployeeDetails(datas);
					finalMap.put(data.getLocationName(), data);
				}
			}
			return finalMap;
		} else if (!attdTypeValue.equals(AppConstants.ALL) && attdType.equals(AppConstants.ALL)
				&& !billingStatus.equals(AppConstants.ALL)) {
			AttendanceByLocation attendanceByLocation = attendenceRepo
					.getAttendByLocationAndDateAndBillingStatus(attdTypeValue, billingStatus, dateString);
			List<AttendanceStatus> attendanceStatusList = attendenceRepo
					.getAttendanceStatusByClinetLocationAndCategoryAndAttendanceDate(attdTypeValue, billingStatus,
							fromDate);
			// List<AttendanceStatus> attendanceStatusByAccountName =
			// attendenceRepo.getAttendanceStatusByAccountNameAndAttendanceStatus(attdTypeValue,"marked");
			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByLocation);
			attnPercentageData.setEmployeeDetails(attendanceStatusList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			if (attnPercentageData.getLocationName() != null) {
				finalMap.put(attnPercentageData.getLocationName(), attnPercentageData);
			}
			return finalMap;
		} else if (!attdTypeValue.equals(AppConstants.ALL) && !attdType.equals(AppConstants.ALL)
				&& !billingStatus.equals(AppConstants.ALL)) {
			AttendanceByLocation attendanceByLocation = attendenceRepo
					.getAttendByLocationAndDateAndBillingStatus(attdTypeValue, billingStatus, dateString);
			List<AttendanceStatus> attendanceStatusList = new ArrayList<>();
			if (!attdType.equals(AppConstants.LEAVE)) {
				attendanceStatusList = attendenceRepo
						.getAttendanceStatusByClinetLocationAndAttendanceStatusAndCategoryAndAttendanceDate(
								attdTypeValue, "Not Marked", billingStatus, fromDate);
			} else {
				attendanceStatusList = attendenceRepo
						.getAttendanceStatusByClinetLocationAndAttendanceStatusInAndCategoryAndAttendanceDate(
								attdTypeValue, BcpUtils.getLeaveDbValues(), billingStatus, fromDate);
			}

			AttendanceData attnPercentageData = calculatePercentageParticularAcc(attendanceByLocation);
			List<AttendanceStatus> uniqueList = attendanceStatusList.stream().collect(collectingAndThen(
					toCollection(() -> new TreeSet<>(comparing(AttendanceStatus::getEmployeeId))), ArrayList::new));
			attnPercentageData.setEmployeeDetails(uniqueList);
			Map<String, AttendanceData> finalMap = new TreeMap<>();
			if (attnPercentageData.getLocationName() != null) {
				finalMap.put(attnPercentageData.getLocationName(), attnPercentageData);
			}
			return finalMap;
		}
		return null;

	}

	private AttendanceData calculatePercentageParticularAcc(AttendanceCommon attendanceByAccount) {
		AttendanceData attendanceData = new AttendanceData();
		if (attendanceByAccount != null) {
			double total = attendanceByAccount.getTotal();
			double marked = attendanceByAccount.getMarked();
			double unmarked = attendanceByAccount.getUnmarked();
			double leaveCount = attendanceByAccount.getLeave_count();
			double leaveApprovalPending = attendanceByAccount.getLeave_app_pend();
			double markedPercentage = 0;
			double unmarkedPercentage = 0;
			double leavePercentage = 0;
			double leaveAppPendPercentage = 0;
			if (marked > 0)
				markedPercentage = (marked * 100 / (total - leaveCount));
			if (unmarked > 0)
				unmarkedPercentage = (unmarked * 100 / total);
			if (leaveCount > 0)
				leavePercentage = (leaveCount * 100 / total);
			if (leaveApprovalPending > 0)
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
			if (attendanceByAccount instanceof AttendanceByAccount) {
				AttendanceByAccount atn = (AttendanceByAccount) attendanceByAccount;
				attendanceData.setAccountName(atn.getAccountName());
			} else {
				AttendanceByLocation atn = (AttendanceByLocation) attendanceByAccount;
				attendanceData.setLocationName(atn.getClient_location());
			}
		}
		return attendanceData;
	}

	public List<AttendanceData> calculatePercentage(List<? extends AttendanceCommon> attendenceLst) {
		System.out.println("attendenceLst===>" + attendenceLst);
		double total;
		double marked;
		double unmarked;
		double leaveCount;
		double leaveApprovalPending;
		double markedPercentage = 0;
		double unmarkedPercentage = 0;
		double leavePercentage = 0;
		double leaveAppPendPercentage = 0;

		double overAllTotal = 0;
		double overAllMarked = 0;
		double overAllUnMarked = 0;
		double overAllLeave = 0;
		double overAllLeaveAppPending = 0;
		double overAllMarkedPercentage = 0;
		double overAllUnmarkedPercentage = 0;
		double overAllLeavePercentage = 0;
		double overAllLeaveAppPendPercentage = 0;
		List<AttendanceData> attendanceDataList = new ArrayList<AttendanceData>();
		for (int i = 0; i < attendenceLst.size(); i++) {
			total = attendenceLst.get(i).getTotal();
			marked = attendenceLst.get(i).getMarked();
			unmarked = attendenceLst.get(i).getUnmarked();
			leaveCount = attendenceLst.get(i).getLeave_count();
			leaveApprovalPending = attendenceLst.get(i).getLeave_app_pend();
			if (marked > 0)
				markedPercentage = (marked * 100 / (total - leaveCount));
			else
				markedPercentage = 0;
			if (unmarked > 0)
				unmarkedPercentage = (unmarked * 100 / total);
			else
				unmarkedPercentage = 0;
			if (leaveCount > 0)
				leavePercentage = (leaveCount * 100 / total);
			else
				leavePercentage = 0;
			if (leaveApprovalPending > 0)
				leaveAppPendPercentage = (leaveApprovalPending * 100 / total);
			else
				leaveAppPendPercentage = 0;

			overAllTotal = overAllTotal + total;
			overAllMarked = overAllMarked + marked;
			overAllUnMarked = overAllUnMarked + unmarked;
			overAllLeave = overAllLeave + leaveCount;
			overAllLeaveAppPending = overAllLeaveAppPending + leaveApprovalPending;
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
			if (attendenceLst.get(i) instanceof AttendanceByAccount) {
				AttendanceByAccount atn = (AttendanceByAccount) attendenceLst.get(i);
				attendanceData.setAccountName(atn.getAccountName());
			} else {
				AttendanceByLocation atn = (AttendanceByLocation) attendenceLst.get(i);
				attendanceData.setLocationName(atn.getClient_location());
			}
			attendanceDataList.add(attendanceData);
		}

		if (!attendenceLst.isEmpty() && attendenceLst.get(0) instanceof AttendanceByLocation) {
			if (overAllMarked > 0)
				overAllMarkedPercentage = (overAllMarked * 100 / (overAllTotal - overAllLeave));
			if (overAllUnMarked > 0)
				overAllUnmarkedPercentage = (overAllUnMarked * 100 / overAllTotal);
			if (overAllLeave > 0)
				overAllLeavePercentage = (overAllLeave * 100 / overAllTotal);
			if (overAllLeaveAppPending > 0)
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
			attendanceData.setLocationName(AppConstants.ORGANISATION_WIDE);
			attendanceDataList.add(0, attendanceData);
		}
		return attendanceDataList;

	}

	public List<String> getAccountNames() {
		return attendenceRepo.findDistinctAccountName();
	}

	public List<String> getClientLocations() {
		return attendenceRepo.findDistinctClientLocation();
	}

	public List<String> getClientLocationsByCategory(String category) {
		return attendenceRepo.findDistinctClientLocationByCategory(category);
	}

	public List<DeliverySummary> getDeliverList(Date fromDate) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(fromDate);
		List<DeliverySummary> deliverySummaryList = dailyStatusRepo.findDeliverySummaryByDate(dateString);
		for (int index = 0; index < deliverySummaryList.size(); index++) {
			DeliverySummary deliverySummary = deliverySummaryList.get(index);
			deliverySummary.setPlanList(getSummayByProject(deliverySummary.getAccount(), fromDate, "all"));
		}
		return deliverySummaryList;
	}

	public List<PlanDetailFormData> getSummayByProject(String name, Date date, String statusValue) {
		List<PlanDetailFormData> planDetailList = new ArrayList<PlanDetailFormData>();
		Account accountObj = accountRepo.findByName(name);
		List<Project> projectsList = projecRepo.findByAccountId(accountObj.getId());
		List<DailyStatus> dailyList = new ArrayList<DailyStatus>();
		if (statusValue.equals("all")) {
			List<String> stausList = Arrays.asList("red", "Amber");
			dailyList = dailyStatusRepo.findByDateAndProjectInAndStatusIn(date, projectsList, stausList);
		} else {
			dailyList = dailyStatusRepo.findByDateAndStatusAndProjectIn(date, statusValue, projectsList);
		}
		for (int i = 0; i < dailyList.size(); i++) {
			PlanDetailFormData planData = new PlanDetailFormData();
			planData.setDeliveryChallenge(dailyList.get(i).getChallenges());
			planData.setProjectName(dailyList.get(i).getProject().getName());
			planData.setMilestone(dailyList.get(i).getMilestone());
			planData.setDeliveryMitigationPlan(dailyList.get(i).getMitigationPlan());
			planData.setWfhChallenge(dailyList.get(i).getWfhChallenge());
			planData.setWfhMitigationPlan(dailyList.get(i).getWfhMitigationPlan());
			planData.setKeyDeliverable(dailyList.get(i).getDeliverableOfDay());
			planData.setHiringUpdate(dailyList.get(i).getHiringUpdate());
			planDetailList.add(planData);
		}
		return planDetailList;
	}

	public void addTodaySummary(TodaySummaryDto formaData) {
		TodaySummary summary = createTodaySummaryObj(formaData);
		todaySummaryRepo.save(summary);
	}

	public TodaySummary getTodaySummary(Date date) {
		return todaySummaryRepo.getTodaySummaryByDate(date);
	}

	private TodaySummary createTodaySummaryObj(TodaySummaryDto formaData) {
		TodaySummary todaySummary = getTodaySummary(formaData.getDate());
		if (todaySummary != null) {
			todaySummary.setSummary(formaData.getTodaySummary());
			todaySummary.setSubmittedby(formaData.getSubmittedby());
			return todaySummary;
		} else {
			TodaySummary summary = new TodaySummary();
			summary.setDate(formaData.getDate());
			summary.setSummary(formaData.getTodaySummary());
			summary.setSubmittedby(formaData.getSubmittedby());
			return summary;
		}

	}
}
