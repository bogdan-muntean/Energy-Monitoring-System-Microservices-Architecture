package com.example.Monitoring_Communication_Microservice.services;

import com.example.Monitoring_Communication_Microservice.dto.DeviceDTO;
import com.example.Monitoring_Communication_Microservice.entities.DeviceHourlyLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.Monitoring_Communication_Microservice.repositories.DeviceHourlyLimitRepository;

import java.util.List;

@Service
public class DeviceHourlyLimitsService {
    String DEVICES_API_URL = "http://localhost:8081/devices";

    @Autowired
    private DeviceHourlyLimitRepository deviceHourlyLimitRepository;

    private final RestTemplate restTemplate;

    public DeviceHourlyLimitsService() {
        this.restTemplate = new RestTemplate();
    }

    public void syncDeviceHourlyLimit(DeviceHourlyLimit deviceHourlyLimit) {
        deviceHourlyLimitRepository.save(deviceHourlyLimit);
    }

//    @Scheduled(fixedRate = 60000)
    public void syncDeviceHourlyLimits() {
        // URL-ul endpoint-ului din device_management project
        String deviceManagementUrl = DEVICES_API_URL + "/get-all";

        DeviceDTO[] devices = restTemplate.getForObject(deviceManagementUrl, DeviceDTO[].class);
        if (devices != null) {
            for (DeviceDTO device : devices) {
                try {
                    List<DeviceHourlyLimit> existingLimits = deviceHourlyLimitRepository.findByDeviceId(device.getId());
                        if (!existingLimits.isEmpty()) {
                            DeviceHourlyLimit existingLimit = existingLimits.get(0);
                            existingLimit.setMaxHourlyEnergyConsumption(device.getMaxHourlyEnergyConsumption());
                            deviceHourlyLimitRepository.save(existingLimit);
                        } else {
                            DeviceHourlyLimit newLimit = new DeviceHourlyLimit();
                            newLimit.setDeviceId(device.getId());
                            newLimit.setMaxHourlyEnergyConsumption(device.getMaxHourlyEnergyConsumption());
                            deviceHourlyLimitRepository.save(newLimit);
                        }
                } catch (Exception e) {
                    System.out.println("Failed to add device hourly limit for device ID "
                            + device.getId());
                }
            }
        } else {
            System.out.println("No devices found");
        }
    }

    public Long getUserOfDevice(Long deviceId) {
        String deviceManagementUrl = DEVICES_API_URL + "/get-device/" + deviceId;
        DeviceDTO[] devices = restTemplate.getForObject(deviceManagementUrl, DeviceDTO[].class);
        if (devices != null) {
            return devices[0].getUserId();
        } else {
            System.out.println("No devices found");
            return null;
        }
    }

    public Double getDeviceHourlyLimit(Long deviceId) {
        List<DeviceHourlyLimit> deviceHourlyLimits = deviceHourlyLimitRepository.findByDeviceId(deviceId);
        if (!deviceHourlyLimits.isEmpty()) {
            return deviceHourlyLimits.get(0).getMaxHourlyEnergyConsumption();
        } else {
            System.out.println("Device was not found");
            return null;
        }
    }
}
