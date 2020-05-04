package com.altimetrik.bcp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.altimetrik.bcp.config.BcpPropertyConfig;
import com.altimetrik.bcp.dao.AttendanceRepo;
import com.altimetrik.bcp.entity.AttendanceStatus;
import com.altimetrik.bcp.exception.FileStorageException;
import com.altimetrik.bcp.util.BcpUtils;

@Service
public class FileUploadService {

	@Autowired
	AttendanceRepo attendenceRepo;

	private final Path attendanceFileStoragePath;

	public FileUploadService(BcpPropertyConfig itsConfig) {
		this.attendanceFileStoragePath = Paths.get(itsConfig.getAttendanceFilePath()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.attendanceFileStoragePath);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String storeFile(MultipartFile uploadfile, String uploadFileType) throws Exception {
		String fileName = "";
		try {
			fileName = StringUtils.cleanPath(uploadfile.getOriginalFilename());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			Path targetLocation = null;
			if (uploadFileType.equals("Attendance")) {
				File file = new File(
						"Attendance_" + dateFormat.format(new Date()) + BcpUtils.getFileExtension(fileName));
				targetLocation = this.attendanceFileStoragePath.resolve(file.getPath());
			} else if (uploadFileType.equals("Delivery")) {
				File file = new File("Delivery_" + dateFormat.format(new Date()) + BcpUtils.getFileExtension(fileName));
				targetLocation = this.attendanceFileStoragePath.resolve(file.getPath());
			}
			Files.copy(uploadfile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return targetLocation.toString();
		} catch (IOException ex) {
			throw new FileStorageException("Could not upload file " + fileName + ". Please try again!", ex);
		}

	}

	@SuppressWarnings("static-access")
	public void readAttendanceFromExcel(String file) throws FileStorageException {
		FileInputStream excelFile = null;
		Workbook workbook = null;
		try {
			excelFile = new FileInputStream(new File(file));
			workbook = new XSSFWorkbook(excelFile);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			System.out.println("Read excel Start time:" + new Date());
			List<AttendanceStatus> attendanceList = new ArrayList<>();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				if (nextRow.getRowNum() == 0) {
					continue;
				}
				List<String> list = new ArrayList<>();
				for (int colIndex = 0; colIndex < nextRow.getLastCellNum(); colIndex++) {
					Cell cell = nextRow.getCell(colIndex);
					if (nextRow.getLastCellNum() >= colIndex && cell == null) {
						list.add("");
					} else {
						cell.setCellType(cell.CELL_TYPE_STRING);
						list.add(cell.getStringCellValue());
					}
				}
				if (list.size() != 24) {
					throw new FileStorageException(
							"Uploaded file contains invalid column counts.<br>Expected column count is 24.But actual is "
									+ list.size());
				}
				if (list.get(5).toString().equalsIgnoreCase("INDIA")) {
					AttendanceStatus attendance = new AttendanceStatus();
					attendance.setEmployeeId(list.get(1));
					attendance.setEmpployeeName(list.get(2));
					attendance.setGeography(list.get(3));
					attendance.setAccountName(list.get(4));
					attendance.setCountry(list.get(5));
					attendance.setClinetLocation(list.get(6));
					attendance.setTotalHc(Double.parseDouble(list.get(7)));
					attendance.setProject(list.get(8));
					attendance.setBaseCategory(list.get(9));
					attendance.setCapabilityCenter(list.get(10));
					if (list.get(11) != null && !list.get(11).toString().equals("")) {
						attendance.setBenchWebDate(DateUtil.getJavaDate(Double.parseDouble(list.get(11))));
					}
					attendance.setAssignmentStatus(list.get(12));
					attendance.setCategory(list.get(13));
					if (list.get(14) != null && !list.get(14).toString().equals("")) {
						attendance.setDateOfJoining(DateUtil.getJavaDate(Double.parseDouble(list.get(14))));
					}
					if (list.get(15) != null && !list.get(15).toString().equals("")) {
						attendance.setBenchWebAging(Integer.parseInt(list.get(15)));
					}
					attendance.setPrimarySkill(list.get(16));
					attendance.setSecondarySkill(list.get(17));
					attendance.setTotalExperience(list.get(18));
					attendance.setReportingManager(list.get(19));
					attendance.setBaseLocation(list.get(20));
					attendance.setEmailId(list.get(21));
					if (list.get(22).equalsIgnoreCase("Unmarked") || list.get(22).equalsIgnoreCase("#N/A")
							|| list.get(22).equalsIgnoreCase("Not Marked")) {
						attendance.setAttendanceStatus("Not Marked");
					} else if (list.get(22).equalsIgnoreCase("Leave - Approval Pending")
							|| list.get(22).equalsIgnoreCase("Leave")
							|| list.get(22).equalsIgnoreCase("Floater Holiday")) {
						attendance.setAttendanceStatus("Leave");
					} else {
						attendance.setAttendanceStatus(list.get(22));
					}
					if (list.get(23) != null && !list.get(23).toString().equals("")) {
						attendance.setAttendanceDate(DateUtil.getJavaDate(Double.parseDouble(list.get(23))));
					}
					attendanceList.add(attendance);
				}
			}
			System.out.println("Read excel end time:" + new Date());
			System.out.println("Total records=" + attendanceList.size());

			saveAttendance(attendanceList);
		} catch (Exception e) {
			throw new FileStorageException(e.getLocalizedMessage());
		} finally {
			try {
				workbook.close();
				excelFile.close();
			} catch (IOException e) {
				throw new FileStorageException(e.getLocalizedMessage());
			}
		}
	}

	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

	private List<Date> getUniqueDateList(List<AttendanceStatus> attendanceList) {
		List<Date> listDate = new ArrayList<>();
		Stream<AttendanceStatus> uniqueDateList = attendanceList.stream()
				.filter(distinctByKey(AttendanceStatus::getAttendanceDate));
		uniqueDateList.forEach(f -> {
			listDate.add(f.getAttendanceDate());
		});
		return listDate;
	}

	@Transactional
	public void saveAttendance(List<AttendanceStatus> attendanceList) {

		List<Date> dateList = getUniqueDateList(attendanceList);
		System.out.println("DateList:" + dateList);
		if (null != dateList && !dateList.isEmpty()) {
			System.out.println("Delete records Start time:" + new Date());
			attendenceRepo.deleteByAttendanceDate(dateList);
			System.out.println("Delete records End time:" + new Date());

		}
		System.out.println("Save batch start time:" + new Date());
		attendenceRepo.saveAll(attendanceList);
		System.out.println("Save batch end time:" + new Date());
	}

}