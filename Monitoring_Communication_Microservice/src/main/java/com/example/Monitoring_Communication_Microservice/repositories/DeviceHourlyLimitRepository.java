package com.example.Monitoring_Communication_Microservice.repositories;

import com.example.Monitoring_Communication_Microservice.entities.DeviceHourlyLimit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceHourlyLimitRepository extends CrudRepository<DeviceHourlyLimit, Long> {
    List<DeviceHourlyLimit> findByDeviceId(Long deviceId);
}
