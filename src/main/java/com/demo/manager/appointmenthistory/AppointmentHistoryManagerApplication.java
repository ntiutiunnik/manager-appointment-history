package com.demo.manager.appointmenthistory;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppointmentHistoryManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentHistoryManagerApplication.class, args);
	}
}
