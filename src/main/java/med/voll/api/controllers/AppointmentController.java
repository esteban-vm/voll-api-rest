package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.dto.AppointmentDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Transactional
    @PostMapping
    public ResponseEntity<AppointmentDetailDTO> create(@RequestBody @Valid AppointmentCreateDTO dto) {
        System.out.println(dto);
        return ResponseEntity.ok(new AppointmentDetailDTO(null, null, null, null));
    }

}
