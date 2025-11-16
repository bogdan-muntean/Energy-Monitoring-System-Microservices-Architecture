package com.example.Device_Management.controllers;

import com.example.Device_Management.entities.Device;
import com.example.Device_Management.services.DeviceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/devices")
@CrossOrigin
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get-all")
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/get-device/{deviceId}")
    public ResponseEntity<List<Device>> getDeviceById(@PathVariable Long deviceId) {
        List<Device> devices = deviceService.getDeviceById(deviceId);
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Device>> getDevicesByUserId(@PathVariable Long userId) {
        List<Device> devices = deviceService.getDevicesByUserId(userId);
        if (devices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(devices);
        }
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/add-device")
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        if (device.getUserId() == null) {
            return ResponseEntity.badRequest().body("UserId is required");
        }

        try {
            Device newDevice = deviceService.addDevice(device);
            return ResponseEntity.ok(newDevice);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Returns an error message if an exception occurs
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.ok("Device deleted successfully");
    }

    @DeleteMapping("/user/{userId}/delete-all")
    @Transactional
    public ResponseEntity<Void> deleteDevicesByUserId(@PathVariable Long userId) {
        try {
            deviceService.deleteDevicesByUserId(userId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Device device) {
        deviceService.updateDevice(device);
        return ResponseEntity.ok("Device updated successfully");
    }
}
