package com.smartparking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.smartparking.allocation.Slot;
import com.smartparking.allocation.SlotRepository;

@SpringBootApplication
public class SmartparkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartparkingApplication.class, args);
	}
	@Bean
	CommandLineRunner initSlots(SlotRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				repo.save(new Slot(null, "A1", true, null));
				repo.save(new Slot(null, "A2", true, null));
				repo.save(new Slot(null, "B1", true, null));
			}
		};

	}

}
