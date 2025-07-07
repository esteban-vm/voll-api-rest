package med.voll.api.dto;

import java.time.LocalDateTime;

public record AppointmentDetailDTO(

        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime dateTime

) {
}
