package com.example.Monitoring_Communication_Microservice;

import com.example.Monitoring_Communication_Microservice.components.EnergyDataConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.example.Monitoring_Communication_Microservice.repositories")
public class MonitoringCommunicationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringCommunicationMicroserviceApplication.class, args);
	}

//	public static void main(String[] args) {
//		EnergyDataConsumer consumer = new EnergyDataConsumer();
//		consumer.start();
//	}

}
