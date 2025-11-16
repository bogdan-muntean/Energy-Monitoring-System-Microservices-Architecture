package com.example.Device_Management.repositories;

import com.example.Device_Management.entities.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    List<Device> findByUserId(Long userId);

    List<Device> findDeviceById (Long deviceId);
}
