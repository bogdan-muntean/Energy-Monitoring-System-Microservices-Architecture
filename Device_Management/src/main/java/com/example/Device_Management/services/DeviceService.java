package com.example.Device_Management.services;

import com.example.Device_Management.entities.Device;
import com.example.Device_Management.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Device> getAllDevices() {
        return (List<Device>) deviceRepository.findAll();
    }

    public List<Device> getDevicesByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        return deviceRepository.findByUserId(userId);
    }

    public List<Device> getDeviceById(Long deviceId) {
        if (deviceId == null) {
            throw new IllegalArgumentException("Device ID must not be null");
        }
        return deviceRepository.findDeviceById(deviceId);
    }

    public Device addDevice(Device device) {
        String userExistsUrl = "http://localhost:8080/users/user-exists/" + device.getUserId();

        try {
            Boolean userExists = restTemplate.getForObject(userExistsUrl, Boolean.class);

            if (Boolean.FALSE.equals(userExists)) {
                throw new IllegalArgumentException("User with ID " + device.getUserId() + " does not exist.");
            }

            return deviceRepository.save(device);

        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to add device with user ID " + device.getUserId(), e);
        }
    }

    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    public void deleteDevicesByUserId(Long userId){
        List<Device> devices = deviceRepository.findByUserId(userId);
        deviceRepository.deleteAll(devices);
    }
}
