package in.snyce.gatepass.controller;

import in.snyce.gatepass.controllers.GatepassController;
import in.snyce.gatepass.model.Gatepass;
import in.snyce.gatepass.services.GatepassService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
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
        sampleGatepass.setId(10788);
        sampleGatepass.setUserId(101);
        sampleGatepass.setLocationId(1);
        sampleGatepass.setSubLocationId(2);
        sampleGatepass.setDate(LocalDate.now());
        sampleGatepass.setSiteType("TechPark");
        sampleGatepass.setSite("T-Block");
        sampleGatepass.setGatePassNo("GP-00987");
        sampleGatepass.setDocumentNo("DOC-33221");
        sampleGatepass.setRequestorId(202);
        sampleGatepass.setRequestorName("Ichigo");
        sampleGatepass.setVendorName("Dell Supplier");
        sampleGatepass.setVendorContactNo("7894561230");
        sampleGatepass.setSiteAddress("Sector 5, Industrial Layout");
        sampleGatepass.setBuilingMangerContact("9876543210");
        sampleGatepass.setVendorAddress("Dell Industrial, Koramangala");
        sampleGatepass.setCategory("Returnable");
        sampleGatepass.setAttachment("file.pdf");
        sampleGatepass.setReturnAcceptanceDate(LocalDateTime.now().plusDays(5));
        sampleGatepass.setAssetDetails("DELL_LATITUDE_LAPTOP, 1, $1200");
        sampleGatepass.setInwardReport("No damage");
        sampleGatepass.setOutwardReport("Clean condition");
        sampleGatepass.setStatus("Pending");
        sampleGatepass.setStatusStep(1);
        sampleGatepass.setRequestorClosedUpload("image.png");
        sampleGatepass.setCreatedAt(LocalDateTime.now().minusDays(1));
        sampleGatepass.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void testCreateGatepass() throws Exception {
        log.info("Testing: Create Gatepass");
        when(gatepassService.createGatepass(any(Gatepass.class))).thenReturn(sampleGatepass);

        String payload = """
                {
                  "userId": 101,
                  "locationId": 1,
                  "subLocationId": 2,
                  "date": "2025-04-16",
                  "siteType": "TechPark",
                  "site": "T-Block",
                  "gatePassNo": "GP-00987",
                  "documentNo": "DOC-33221",
                  "requestorId": 202,
                  "requestorName": "Ichigo",
                  "vendorName": "Dell Supplier",
                  "vendorContactNo": "7894561230",
                  "siteAddress": "Sector 5, Industrial Layout",
                  "builingMangerContact": "9876543210",
                  "vendorAddress": "Dell Industrial, Koramangala",
                  "category": "Returnable",
                  "attachment": "file.pdf",
                  "assetDetails": "DELL_LATITUDE_LAPTOP, 1, $1200",
                  "inwardReport": "No damage",
                  "outwardReport": "Clean condition",
                  "status": "Pending",
                  "statusStep": 1,
                  "requestorClosedUpload": "image.png"
                }""";

        mockMvc.perform(post("/api/gatepass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllGatepasses() throws Exception {
        log.info("Testing: Get All Gatepasses");
        List<Gatepass> gatepasses = Arrays.asList(sampleGatepass);
        when(gatepassService.getAllGatepasses()).thenReturn(gatepasses);

        mockMvc.perform(get("/api/gatepass"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testGetGatepassById() throws Exception {
        log.info("Testing: Get Gatepass by ID");
        when(gatepassService.getGatepassById(10788L)).thenReturn(sampleGatepass);

        mockMvc.perform(get("/api/gatepass/10788"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    /*@Test
    void testUpdateStatus() throws Exception {
        log.info("Testing: Update Gatepass Status");
        Gatepass updated = sampleGatepass;
        updated.setStatus("Approved");

        when(gatepassService.updateStatus(10788L, "Approved")).thenReturn(updated);

        mockMvc.perform(put("/api/gatepasses/update/10788/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"Approved\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }*/
}
