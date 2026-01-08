package com.example.temperature_dashboard.repository;

import com.example.temperature_dashboard.repository.TemperatureRepository;
import com.example.temperature_dashboard.model.TemperatureReading;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends CassandraRepository<TemperatureReading, String> {
}
