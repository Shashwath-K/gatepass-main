package in.snyce.gatepass.controller;

import in.snyce.gatepass.model.Gatepass;
import in.snyce.gatepass.services.GatepassService;
import in.snyce.gatepass.controllers.GatepassController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GatepassControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GatepassService gatepassService;

    @InjectMocks
    private GatepassController gatepassController;

    private Gatepass sampleGatepass;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gatepassController).build();

        sampleGatepass = new Gatepass();
        sampleGatepass.setId(1L);
        sampleGatepass.setEmployeeName("Ichigo");
        sampleGatepass.setPurpose("Material Transfer");
        sampleGatepass.setRequestTime(LocalDateTime.now());
        sampleGatepass.setStatus("Pending");
    }

    @Test
    void testCreateGatepass() throws Exception {
        when(gatepassService.createGatepass(any(Gatepass.class))).thenReturn(sampleGatepass);

        mockMvc.perform(post("/api/gatepasses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeName\":\"Ichigo\",\"purpose\":\"Material Transfer\",\"status\":\"Pending\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Ichigo"))
                .andExpect(jsonPath("$.status").value("Pending"));
    }

    @Test
    void testGetAllGatepasses() throws Exception {
        List<Gatepass> gatepasses = Arrays.asList(sampleGatepass);
        when(gatepassService.getAllGatepasses()).thenReturn(gatepasses);

        mockMvc.perform(get("/api/gatepasses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].employeeName").value("Ichigo"));
    }

    @Test
    void testGetGatepassById() throws Exception {
        when(gatepassService.getGatepassById(1L)).thenReturn(sampleGatepass);

        mockMvc.perform(get("/api/gatepasses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Ichigo"));
    }

    @Test
    void testUpdateStatus() throws Exception {
        Gatepass updated = sampleGatepass;
        updated.setStatus("Approved");
        when(gatepassService.updateStatus(1L, "Approved")).thenReturn(updated);

        mockMvc.perform(put("/api/gatepasses/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"Approved\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Approved"));
    }
}
