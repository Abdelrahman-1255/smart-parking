package com.smartparking.entry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingEntryRepository extends JpaRepository<ParkingEntry, Long> {
	Optional<ParkingEntry> findFirstByVehicleNumberAndActiveTrueOrderByEntryTimeDesc(String vehicleNumber);
}
