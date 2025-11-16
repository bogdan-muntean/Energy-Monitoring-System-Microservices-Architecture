package com.example.Monitoring_Communication_Microservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class DeviceDTO {
    @Id
    private Long id; // ID-ul dispozitivului
    private String description; // Descrierea dispozitivului
    private String address; // Adresa dispozitivului
    private double maxHourlyEnergyConsumption; // Consumul maxim pe oră
    private Long userId; // ID-ul utilizatorului asociat

    // Constructor gol
    public DeviceDTO() {}

    // Constructor complet
    public DeviceDTO(Long id, String description, String address, double maxHourlyEnergyConsumption, Long userId) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxHourlyEnergyConsumption = maxHourlyEnergyConsumption;
        this.userId = userId;
    }

    // Getters și Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMaxHourlyEnergyConsumption() {
        return maxHourlyEnergyConsumption;
    }

    public void setMaxHourlyEnergyConsumption(double maxHourlyEnergyConsumption) {
        this.maxHourlyEnergyConsumption = maxHourlyEnergyConsumption;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DeviceDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", maxHourlyEnergyConsumption=" + maxHourlyEnergyConsumption +
                ", userId=" + userId +
                '}';
    }
}
