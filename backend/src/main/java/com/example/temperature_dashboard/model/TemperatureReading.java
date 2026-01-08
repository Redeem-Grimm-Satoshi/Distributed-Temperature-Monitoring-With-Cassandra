package com.example.temperature_dashboard.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.Instant;

@Data  // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor  // generates no-args constructor
@AllArgsConstructor // generates all-args constructor
@Table("temperature_readings")
public class TemperatureReading {

    @PrimaryKey
    private String id; // UUID or node identifier + timestamp
    private String nodeName;
    private String ipAddress;
    private double temperature;
    private boolean online;
    private Instant timestamp;
}
