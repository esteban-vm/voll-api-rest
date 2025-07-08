package med.voll.api.repositories;

import med.voll.api.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByDoctorIdAndDateTime(Long idDoctor, LocalDateTime dateTime);

    Boolean existsByPatientIdAndDateTimeBetween(Long idPatient, LocalDateTime firstSchedule, LocalDateTime lastSchedule);

}
