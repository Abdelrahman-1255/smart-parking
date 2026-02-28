package com.smartparking.allocation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    Optional<Slot> findFirstByAvailableTrue();

    Optional<Slot> findByVehicleNumber(String vehicleNumber);
}
