package med.voll.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.enums.MedicalSpecialty;

import java.time.LocalDateTime;

public record AppointmentCreateDTO(

        Long idDoctor,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime dateTime,

        MedicalSpecialty specialty

) {
}
