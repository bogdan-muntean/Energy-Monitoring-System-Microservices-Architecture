package com.example.Monitoring_Communication_Microservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "device_hourly_limits")
public class DeviceHourlyLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    @Column(name = "max_hourly_energy_consumption", nullable = false)
    private double maxHourlyEnergyConsumption;

    // Constructor gol (necesar pentru JPA)
    public DeviceHourlyLimit() {
    }

    // Constructor complet
    public DeviceHourlyLimit(Long deviceId, double maxHourlyEnergyConsumption) {
        this.deviceId = deviceId;
        this.maxHourlyEnergyConsumption = maxHourlyEnergyConsumption;
    }

    // Getters și Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public double getMaxHourlyEnergyConsumption() {
        return maxHourlyEnergyConsumption;
    }

    public void setMaxHourlyEnergyConsumption(double maxHourlyEnergyConsumption) {
        this.maxHourlyEnergyConsumption = maxHourlyEnergyConsumption;
    }

    @Override
    public String toString() {
        return "DeviceHourlyLimit{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", maxHourlyEnergyConsumption=" + maxHourlyEnergyConsumption +
                '}';
    }
}
