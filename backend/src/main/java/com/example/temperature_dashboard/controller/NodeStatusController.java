package com.example.temperature_dashboard.controller;

import com.example.temperature_dashboard.model.NodeStatus;
import com.example.temperature_dashboard.repository.NodeStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/status")
@RequiredArgsConstructor
public class NodeStatusController {

    // Repository for NodeStatus entities
    private final NodeStatusRepository repo;

    public record StatusDto(String ipAddress, String nodeName, Instant lastSeen, boolean online) {}

    // Heartbeat endpoint
    @PostMapping("/heartbeat")
    public StatusDto heartbeat(@RequestBody NodeStatus incoming) {
        NodeStatus saved = new NodeStatus(
            incoming.getIpAddress(),
            incoming.getNodeName() != null ? incoming.getNodeName() : "Unknown",
            Instant.now()
        );
        repo.save(saved);
        return new StatusDto(saved.getIpAddress(), saved.getNodeName(), saved.getLastSeen(), true);
    }

    // Fetch all node statuses
    @GetMapping
    public List<StatusDto> allStatuses() {
        Instant now = Instant.now();
        return repo.findAll().stream()
            .map(s -> {
                boolean online = s.getLastSeen() != null &&
                        Duration.between(s.getLastSeen(), now).toSeconds() < 10;
                return new StatusDto(s.getIpAddress(), s.getNodeName(), s.getLastSeen(), online);
            })
            .collect(Collectors.toList());
    }
}
