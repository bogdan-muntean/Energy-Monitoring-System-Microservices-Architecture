package com.example.Monitoring_Communication_Microservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "hourly_consumption")
public class HourlyConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    @Column(name = "timestamp", nullable = false)
    private Long timestamp;

    @Column(name = "total_consumption", nullable = false)
    private double totalConsumption;

    // Constructor implicit (necesar pentru JPA)
    public HourlyConsumption() {}

    // Constructor complet
    public HourlyConsumption(Long deviceId, Long timestamp, double totalConsumption) {
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.totalConsumption = totalConsumption;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    @Override
    public String toString() {
        return "HourlyConsumption{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", timestamp=" + timestamp +
                ", totalConsumption=" + totalConsumption +
                '}';
    }
}