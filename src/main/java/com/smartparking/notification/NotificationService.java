package com.smartparking.notification;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.smartparking.event.VehicleEnteredEvent;
import com.smartparking.event.VehicleExitedEvent;

@Service
public class NotificationService {
    
    @EventListener
    public void notifyOnVehicleEntry(VehicleEnteredEvent event) {
        // Logic to send notification about vehicle entry
        System.out.println("Notification: Vehicle entered - " + event.vehicleNumber() + " at " + event.entryTime());
    }

    @EventListener
    public void notifyOnVehicleExit(VehicleExitedEvent event) {
        // Logic to send notification to the user
        System.out.println("📩 Notification: Vehicle " + event.vehicleNumber() + " has exited. Thank you for visiting!");
    }
}
