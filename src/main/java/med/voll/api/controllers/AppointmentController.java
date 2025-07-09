package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.dto.AppointmentDeleteDTO;
import med.voll.api.dto.AppointmentDetailDTO;
import med.voll.api.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @Transactional
    @PostMapping
    public ResponseEntity<AppointmentDetailDTO> create(@RequestBody @Valid AppointmentCreateDTO dto) {
        var appointmentDetail = service.bookAppointment(dto);
        return ResponseEntity.ok(appointmentDetail);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<AppointmentDetailDTO> cancel(@RequestBody @Valid AppointmentDeleteDTO dto) {
        service.cancelAppointment(dto);
        return ResponseEntity.noContent().build();
    }

}
