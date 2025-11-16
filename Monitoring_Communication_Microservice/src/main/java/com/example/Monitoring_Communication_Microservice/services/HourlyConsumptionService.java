package com.example.Monitoring_Communication_Microservice.services;

import com.example.Monitoring_Communication_Microservice.entities.HourlyConsumption;
import com.example.Monitoring_Communication_Microservice.repositories.HourlyConsumptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HourlyConsumptionService {

    @Autowired
    private HourlyConsumptionRepository hourlyConsumptionRepository;

    public HourlyConsumptionService() {
    }

//    public void saveHourlyConsumptionToDB(Long deviceId, Long timestamp, double consumption) {
//        List<HourlyConsumption> existingRecords = hourlyConsumptionRepository.findByDeviceIdAndTimestamp(deviceId, timestamp);
//        if (existingRecords.isEmpty()) {
//            hourlyConsumptionRepository.save(new HourlyConsumption(deviceId, timestamp, consumption));
//        } else {
//            HourlyConsumption existingRecord = existingRecords.get(0);
//            existingRecord.setTotalConsumption(consumption);
//            hourlyConsumptionRepository.save(existingRecord);
//        }
//    }

    public void save(HourlyConsumption newHourlyConsumption) {
            hourlyConsumptionRepository.save(newHourlyConsumption);
    }
}
