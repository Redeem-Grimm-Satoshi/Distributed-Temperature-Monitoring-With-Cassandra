package com.example.temperature_dashboard.controller;

import com.example.temperature_dashboard.model.TemperatureReading;
import com.example.temperature_dashboard.repository.TemperatureRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TemperatureController.class)
class TemperatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TemperatureRepository repository;

    @Test
    void getAllReadingsMarksStaleOrMissingAsOffline() throws Exception {
        TemperatureReading recent = new TemperatureReading(
                "id-1", "node-1", "10.0.0.1", 22.5, true, Instant.now().minusSeconds(10)
        );
        TemperatureReading stale = new TemperatureReading(
                "id-2", "node-2", "10.0.0.2", 21.0, true, Instant.now().minusSeconds(120)
        );
        TemperatureReading missingTimestamp = new TemperatureReading(
                "id-3", "node-3", "10.0.0.3", 20.0, true, null
        );

        when(repository.findAll()).thenReturn(List.of(recent, stale, missingTimestamp));

        mockMvc.perform(get("/api/temperatures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("id-1"))
                .andExpect(jsonPath("$[0].online").value(true))
                .andExpect(jsonPath("$[1].id").value("id-2"))
                .andExpect(jsonPath("$[1].online").value(false))
                .andExpect(jsonPath("$[2].id").value("id-3"))
                .andExpect(jsonPath("$[2].online").value(false));
    }

    @Test
    void addReadingAssignsIdAndTimestamp() throws Exception {
        when(repository.save(any(TemperatureReading.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        String payload = """
                {
                  "nodeName": "node-1",
                  "ipAddress": "10.0.0.1",
                  "temperature": 23.1,
                  "online": true
                }
                """;

        mockMvc.perform(post("/api/temperatures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.timestamp").isNotEmpty());

        ArgumentCaptor<TemperatureReading> captor = ArgumentCaptor.forClass(TemperatureReading.class);
        verify(repository).save(captor.capture());
        TemperatureReading saved = captor.getValue();
        assertThat(saved.getId()).isNotBlank();
        assertThat(saved.getTimestamp()).isNotNull();
    }

    @Test
    void testEndpointReturnsMessage() throws Exception {
        mockMvc.perform(get("/api/temperatures/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Controller is working!"));
    }
}
