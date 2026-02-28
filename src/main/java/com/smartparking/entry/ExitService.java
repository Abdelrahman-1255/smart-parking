package com.smartparking.entry;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.smartparking.event.VehicleExitedEvent;

@Service
public class ExitService {
    private final ParkingEntryRepository repository;
    private final ApplicationEventPublisher publisher;

    public ExitService(ParkingEntryRepository repository,
                       ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @EventListener
    public void vehicleExit(String vehicleNumber) {
        // get vehicle entry details from DB
        // update exit time
        // save to db
        //publish vehicle exited event
        ParkingEntry entry = repository.findFirstByVehicleNumberAndActiveTrueOrderByEntryTimeDesc(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("No active entry found for vehicle " + vehicleNumber));

        entry.setExitTime(LocalDateTime.now());
        entry.setActive(false);
        repository.save(entry);
        publisher.publishEvent(new VehicleExitedEvent(vehicleNumber, entry.getEntryTime(), entry.getExitTime()));
    }
}
