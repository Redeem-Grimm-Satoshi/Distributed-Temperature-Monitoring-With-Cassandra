package com.example.temperature_dashboard.repository;

import com.example.temperature_dashboard.model.NodeStatus;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeStatusRepository extends CassandraRepository<NodeStatus, String> {
}
