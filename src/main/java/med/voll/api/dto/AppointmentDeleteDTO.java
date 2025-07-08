package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.enums.CancellationReason;

public record AppointmentDeleteDTO(

        @NotNull
        Long idAppointment,

        @NotNull
        CancellationReason reason

) {
}
