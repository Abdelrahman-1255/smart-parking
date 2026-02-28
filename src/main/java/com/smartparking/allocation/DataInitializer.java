package com.smartparking.allocation;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.smartparking.entry.ParkingEntryRepository;

@Component
public class DataInitializer implements ApplicationRunner {

    private final SlotRepository slotRepository;
    private final ParkingEntryRepository parkingEntryRepository;

    public DataInitializer(SlotRepository slotRepository, ParkingEntryRepository parkingEntryRepository) {
        this.slotRepository = slotRepository;
        this.parkingEntryRepository = parkingEntryRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (slotRepository.count() == 0) {
            List<Slot> slots = List.of(
                new Slot(null, "A1", true, null),
                new Slot(null, "A2", true, null),
                new Slot(null, "A3", true, null),
                new Slot(null, "B1", true, null),
                new Slot(null, "B2", true, null),
                new Slot(null, "B3", true, null)
            );
            slotRepository.saveAll(slots);
            System.out.println("Seeded " + slots.size() + " parking slots.");
        } else {
            // Reset all slots to available — clears up any stuck state from failed runs
            slotRepository.findAll().forEach(slot -> {
                slot.setAvailable(true);
                slot.setVehicleNumber(null);
                slotRepository.save(slot);
            });
            // Close all stale active parking entries
            parkingEntryRepository.findAll().stream()
                .filter(entry -> entry.isActive())
                .forEach(entry -> {
                    entry.setActive(false);
                    entry.setExitTime(java.time.LocalDateTime.now());
                    parkingEntryRepository.save(entry);
                    System.out.println("Closed stale entry for vehicle: " + entry.getVehicleNumber());
                });
            System.out.println("All parking slots reset to available.");
        }
    }
}
