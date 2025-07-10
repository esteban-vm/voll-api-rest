package med.voll.api.controllers;

import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.dto.AppointmentDetailDTO;
import med.voll.api.enums.MedicalSpecialty;
import med.voll.api.services.AppointmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentCreateDTO> createDTOJacksonTester;

    @Autowired
    private JacksonTester<AppointmentDetailDTO> detailDTOJacksonTester;

    @MockitoBean
    private AppointmentService service;

    @Test
    @DisplayName("Debería devolver http 400 cuando la solicitud no obtenga datos")
    @WithMockUser
    void createCase1() throws Exception {
        var response = mvc
                .perform(post("/appointments"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Debería devolver http 200 cuando la solicitud reciba un JSON válido")
    @WithMockUser
    void createCase2() throws Exception {
        var dateTime = LocalDateTime.now().plusHours(1);
        var speciality = MedicalSpecialty.CARDIOLOGIA;
        var detail = new AppointmentDetailDTO(null, 2L, 5L, dateTime);
        when(service.bookAppointment(any())).thenReturn(detail);

        var response = mvc
                .perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createDTOJacksonTester
                                .write(new AppointmentCreateDTO(2L, 5L, dateTime, speciality))
                                .getJson()))
                .andReturn()
                .getResponse();

        var expectedJson = detailDTOJacksonTester.write(detail).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

}
