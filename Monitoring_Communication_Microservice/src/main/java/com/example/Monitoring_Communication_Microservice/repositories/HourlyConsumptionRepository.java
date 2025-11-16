package com.example.Monitoring_Communication_Microservice.repositories;

import com.example.Monitoring_Communication_Microservice.entities.HourlyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HourlyConsumptionRepository extends JpaRepository<HourlyConsumption, Long> {
    List<HourlyConsumption> findByDeviceIdAndTimestamp(Long deviceId, Long timestamp);
}