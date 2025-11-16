package com.example.Monitoring_Communication_Microservice.components;

//import com.example.Monitoring_Communication_Microservice.services.WebSocketNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import com.example.Monitoring_Communication_Microservice.dto.Measurement;
import com.example.Monitoring_Communication_Microservice.entities.HourlyConsumption;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.Monitoring_Communication_Microservice.services.DeviceHourlyLimitsService;
import com.example.Monitoring_Communication_Microservice.services.HourlyConsumptionService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class EnergyDataConsumer {
    @Autowired
    private HourlyConsumptionService hourlyConsumptionService;

    @Autowired
    private DeviceHourlyLimitsService deviceHourlyLimitsService;

//    @Autowired
//    private WebSocketNotificationService webSocketNotificationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    private final Map<String, List<String>> userAlerts = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "energy_data")
    private void handleEnergyData(String messageBody) {
        try {
            System.out.println("Sincronizare Device Hourly Limits: ");
            deviceHourlyLimitsService.syncDeviceHourlyLimits();
            System.out.println("Mesaj primit din coada: " + messageBody);

            Measurement measurement = objectMapper.readValue(messageBody, Measurement.class);
            System.out.println("Measurement deserializat: " + measurement);

            HourlyConsumption newHourlyConsumption = new HourlyConsumption(
                    measurement.getDeviceId(),
                    measurement.getTimestamp(),
                    measurement.getMeasurementValue()
            );

            hourlyConsumptionService.save(newHourlyConsumption);

            Double maxHourlyLimit = deviceHourlyLimitsService.getDeviceHourlyLimit(measurement.getDeviceId());
            if (maxHourlyLimit != null && measurement.getMeasurementValue() > maxHourlyLimit) {
                System.out.println("Avertisment: Consum maxim depasit pentru dispozitivul " +
                        measurement.getDeviceId() + " cu valoarea " + measurement.getMeasurementValue());

                long millis = measurement.getTimestamp();
                String hms = convertMillisToHhMmSs(millis);

                String message = String.format("Device ID " + measurement.getDeviceId()
                        + " exceeded max hourly consumption limit with " + measurement.getMeasurementValue()
                        + " at " + hms + "!"
                );

                Long userId = deviceHourlyLimitsService.getUserOfDevice(measurement.getDeviceId());
//                Long userId = deviceHourlyLimitsService.getUserOfDevice();
//                String destination = "/alert/" + userId;
                if (userId != null) {
                    String destination = "/alert/" + userId;
                    System.out.println("Trimitere mesaj către destinația: " + destination);
                    messagingTemplate.convertAndSend(destination, message);
                } else {
                    System.err.println("User ID este null pentru dispozitivul: " + measurement.getDeviceId());
                }
//                messagingTemplate.convertAndSend(destination, message);
//                messagingTemplate.convertAndSend("/topic/messages", message);
            }
        } catch (Exception e) {
            System.err.println("Eroare la procesarea mesajului din coada: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    public List<String> getAlertsForUser(String userId) {
//        return userAlerts.remove(userId); // Șterge alertele după ce sunt trimise
//    }

    public static String convertMillisToHhMmSs(long millis) {
        long totalSeconds = millis / 1000;
        long s = totalSeconds % 60;
        long m = (totalSeconds / 60) % 60;
        long h = (totalSeconds / (60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", h,m,s);
    }
}