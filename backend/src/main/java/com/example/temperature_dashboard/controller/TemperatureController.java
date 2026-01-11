package com.example.temperature_dashboard.controller;

import com.example.temperature_dashboard.model.TemperatureReading;
import com.example.temperature_dashboard.repository.TemperatureRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/temperatures")
public class TemperatureController {

    // Handles REST operations for temperature readings.
    private final TemperatureRepository repository;

    public TemperatureController(TemperatureRepository repository) {
        this.repository = repository;
    }

    // Get all readings
    @GetMapping(produces = "application/json")
    public List<TemperatureReading> getAllReadings() {
        List<TemperatureReading> readings = repository.findAll();

        // Mark nodes as offline if their last reading is stale.
        Instant now = Instant.now();
        for (TemperatureReading r : readings) {
            // If no update in last 60s, mark as offline
            if (r.getTimestamp() == null || Duration.between(r.getTimestamp(), now).toSeconds() > 60) {
                r.setOnline(false);
            }
        }
    
        return readings;
       // return repository.findAll();
    }

    // Insert new reading
    @PostMapping
    public TemperatureReading addReading(@RequestBody TemperatureReading reading) {
        // Ensure a unique ID and set the server-side timestamp before saving.
        if (reading.getId() == null) {
            reading.setId(UUID.randomUUID().toString());
        }
        reading.setTimestamp(Instant.now());
        return repository.save(reading);
    }

    // Simple health check endpoint.
    @GetMapping("/test") 
    public String test() { return "Controller is working!"; }
}
