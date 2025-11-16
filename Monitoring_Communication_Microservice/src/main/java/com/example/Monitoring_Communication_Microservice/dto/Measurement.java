package com.example.Monitoring_Communication_Microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Measurement {
    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("device_id")
    private Long deviceId;

    @JsonProperty("measurement_value")
    private double measurementValue;

    // Constructor gol (necesar pentru deserializare)
    public Measurement() {
    }

    // Constructor complet
    public Measurement(Long deviceId, long timestamp, double measurementValue) {
        this.timestamp = timestamp;
        this.deviceId = deviceId;
        this.measurementValue = measurementValue;
    }

    // Getter pentru timestamp
    public long getTimestamp() {
        return timestamp;
    }

    // Setter pentru timestamp
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // Getter pentru deviceId
    public Long getDeviceId() {
        return deviceId;
    }

    // Setter pentru deviceId
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    // Getter pentru measurementValue
    public double getMeasurementValue() {
        return measurementValue;
    }

    // Setter pentru measurementValue
    public void setMeasurementValue(double measurementValue) {
        this.measurementValue = measurementValue;
    }

    // Metodă pentru generarea unei chei bazate pe timestamp
    private String getHourKey(long timestamp) {
        // Conversie timestamp în format oră (e.g., "2024-11-18T15:00")
        return java.time.Instant.ofEpochMilli(timestamp)
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime()
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .toString();
    }

    // Suprascriere toString pentru debugging
    @Override
    public String toString() {
        return "Measurement{" +
                "timestamp=" + timestamp +
                ", deviceId=" + deviceId +
                ", measurementValue=" + measurementValue +
                '}';
    }
}
