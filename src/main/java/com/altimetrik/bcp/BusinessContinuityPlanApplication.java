package com.altimetrik.bcp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author smehta
 *
 */

@SpringBootApplication
public class BusinessContinuityPlanApplication {

	private static final Logger logger = LoggerFactory.getLogger(BusinessContinuityPlanApplication.class);

	public static void main(String[] args) {
		deleteLogFile();
		SpringApplication.run(BusinessContinuityPlanApplication.class, args);
	}

	/*
	 * Auto delete log file with a period of time
	 */
	public static void deleteLogFile() {
		Properties prop = null;
		prop = new Properties();
		URL url = ClassLoader.getSystemResource("application.properties");
		try {
			prop.load(url.openStream());
		} catch (IOException e) {
			logger.error("File not found" + e);
		}
		String acrchivefileDirectory = prop.getProperty("log-file-archived-path");

		Calendar calender = Calendar.getInstance();
		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH) + 1;

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.of(year, month, 30);

		File directory = new File(acrchivefileDirectory);
		File[] fList = directory.listFiles();

		if (fList != null) {
			for (File file : fList) {
				if (startDate.compareTo(endDate) == 0) {
					file.delete();
					logger.info("Log file deleted");
				} else {
					file.exists();
				}
			}
		}
	}
}
