package com.example.temperature_dashboard.controller;

import com.example.temperature_dashboard.model.NodeStatus;
import com.example.temperature_dashboard.repository.NodeStatusRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NodeStatusController.class)
class NodeStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NodeStatusRepository repository;

    @Test
    void heartbeatDefaultsNodeNameAndReturnsOnline() throws Exception {
        when(repository.save(any(NodeStatus.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        String payload = """
                {
                  "ipAddress": "10.0.0.5"
                }
                """;

        mockMvc.perform(post("/api/status/heartbeat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ipAddress").value("10.0.0.5"))
                .andExpect(jsonPath("$.nodeName").value("Unknown"))
                .andExpect(jsonPath("$.online").value(true))
                .andExpect(jsonPath("$.lastSeen").isNotEmpty());

        ArgumentCaptor<NodeStatus> captor = ArgumentCaptor.forClass(NodeStatus.class);
        verify(repository).save(captor.capture());
        NodeStatus saved = captor.getValue();
        assertThat(saved.getNodeName()).isEqualTo("Unknown");
        assertThat(saved.getLastSeen()).isNotNull();
    }

    @Test
    void allStatusesComputesOnlineFlag() throws Exception {
        Instant now = Instant.now();
        NodeStatus online = new NodeStatus("10.0.0.1", "node-1", now.minusSeconds(5));
        NodeStatus offline = new NodeStatus("10.0.0.2", "node-2", now.minusSeconds(15));
        NodeStatus missing = new NodeStatus("10.0.0.3", "node-3", null);

        when(repository.findAll()).thenReturn(List.of(online, offline, missing));

        mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ipAddress").value("10.0.0.1"))
                .andExpect(jsonPath("$[0].online").value(true))
                .andExpect(jsonPath("$[1].ipAddress").value("10.0.0.2"))
                .andExpect(jsonPath("$[1].online").value(false))
                .andExpect(jsonPath("$[2].ipAddress").value("10.0.0.3"))
                .andExpect(jsonPath("$[2].online").value(false));
    }
}
