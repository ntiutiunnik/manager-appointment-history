package com.demo.manager.appointmenthistory;


import com.demo.manager.appointmenthistory.config.api.SwaggerConfigurationListener;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition
public class AppointmentHistoryManagerApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(AppointmentHistoryManagerApplication.class);
		springApplication.addListeners(new SwaggerConfigurationListener());
		springApplication.run(args);
	}
}
