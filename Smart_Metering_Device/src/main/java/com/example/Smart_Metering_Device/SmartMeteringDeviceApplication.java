package com.example.Smart_Metering_Device;

import components.Measurement;
import components.MessageProducer;
import components.SensorReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartMeteringDeviceApplication {
	// Exemplu de apelare a comenzii
//	mvn spring-boot:run -Dspring-boot.run.arguments="--port=8084 start-device sensor.csv 1"
//	mvn spring-boot:run -Dspring-boot.run.arguments="--port=8085 start-device sensor.csv 6"

	public static void main(String[] args) {
		String port = "8080"; // Port implicit

		for (String arg : args) {
			if (arg.startsWith("--port=")) {
				port = arg.substring("--port=".length());
				break;
			}
		}

		// Setează portul în proprietățile aplicației
		System.setProperty("server.port", port);
		System.out.println("Using port: " + port);

		if (args.length == 0) {
			System.out.println("No command provided. Use 'start-device <csvFilePath> <deviceId>'");
			return;
		}

		String command = args[1];
		if ("start-device".equals(command)) {
			if (args.length < 4) {
				System.out.println("Usage: start-device <csvFilePath> <deviceId>");
			} else {
				String csvFilePath = args[2];
				String deviceId = args[3];
				SmartMeteringDeviceApplication app = new SmartMeteringDeviceApplication();
				app.startDeviceSimulation(csvFilePath, deviceId);
			}
		} else {
			System.out.println("Unknown command: " + command);
		}

		SpringApplication.run(SmartMeteringDeviceApplication.class, args);
	}

	public void startDeviceSimulation(String csvFileName, String deviceId) {
		System.out.println("Starting device simulation");
		String rabbitMqHost = "localhost";
		System.out.println("CSV File Path: " + csvFileName);
		System.out.println("Device ID: " + deviceId);

		try {
			String csvFilePath = "C:\\code-master-sd\\2.SD-Project\\Smart_Metering_Device\\src\\main\\java\\assets\\" + csvFileName;
			SensorReader reader = new SensorReader(csvFilePath);
			MessageProducer producer = new MessageProducer(rabbitMqHost); // RabbitMQ pe localhost

			while (reader.hasNextMeasurement()) {
				Measurement measurement = reader.nextMeasurement();
				System.out.println("Measurement: " + measurement);
				producer.sendMeasurement(deviceId, measurement);
				Thread.sleep(5000); // Așteaptă 10 secunde
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}