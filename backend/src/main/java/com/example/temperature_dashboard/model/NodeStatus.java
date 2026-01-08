package com.example.temperature_dashboard.model;

import lombok.*;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("node_status")
public class NodeStatus {
    @PrimaryKey
    @Column("ip_address")
    private String ipAddress;

    @Column("node_name")
    private String nodeName;
    
    @Column("last_seen")
    private Instant lastSeen;
}
