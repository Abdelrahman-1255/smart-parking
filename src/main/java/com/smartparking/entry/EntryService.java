package com.smartparking.entry;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.smartparking.event.VehicleEnteredEvent;

@Service
public class EntryService {
    // save vehicle entry details to DB
    // allocate a parking slot
    // send notification to the user

    private final ParkingEntryRepository repository;
    private final ApplicationEventPublisher publisher;

    public EntryService(ParkingEntryRepository repository,
                        ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }


    public void vehicleEntry(String vehicleNumber) {
        ParkingEntry parkingEntry = new ParkingEntry(null, vehicleNumber, LocalDateTime.now(), null, true);
        repository.save(parkingEntry);
        // publish an event
        publisher.publishEvent(new VehicleEnteredEvent(vehicleNumber, parkingEntry.getEntryTime()));

    }

}
