package com.altimetrik.bcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BusinessContinuityPlanApplication {
	public static void main(String[] args) {
		SpringApplication.run(BusinessContinuityPlanApplication.class, args);
	}
}
