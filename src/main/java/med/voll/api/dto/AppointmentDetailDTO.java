package med.voll.api.dto;

import med.voll.api.models.Appointment;

import java.time.LocalDateTime;

public record AppointmentDetailDTO(

        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime dateTime

) {

    public AppointmentDetailDTO(Appointment appointment) {
        this(
                appointment.getId(),
                appointment.getDoctor().getId(),
                appointment.getPatient().getId(),
                appointment.getDateTime()
        );
    }

}
