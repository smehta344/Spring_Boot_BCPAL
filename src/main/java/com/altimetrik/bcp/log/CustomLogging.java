package com.altimetrik.bcp.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogging {

	private static final Logger logger = LoggerFactory.getLogger(CustomLogging.class);

	// @Async
	public static void asyncLogger(String message, Object data, Object className) {

		System.out.println("Logger Message :---->");
		logger.info(message + " <------> " + className + " <------> " + data);

		// logger.info("Hello from Log4j - message :", () -> data);
	}
}
