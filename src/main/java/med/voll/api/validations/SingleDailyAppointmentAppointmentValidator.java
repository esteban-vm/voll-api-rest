package med.voll.api.validations;

import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.infra.exceptions.ValidationException;
import med.voll.api.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingleDailyAppointmentAppointmentValidator implements IAppointmentValidator {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(AppointmentCreateDTO dto) throws ValidationException {
        var firstSchedule = dto.dateTime().withHour(7);
        var lastSchedule = dto.dateTime().withHour(18);
        var isAlreadyScheduled = repository.existsByPatientIdAndDateTimeBetween(dto.idPatient(), firstSchedule, lastSchedule);

        if (isAlreadyScheduled) {
            throw new ValidationException("El paciente ya tiene una consulta para ese día");
        }
    }

}
