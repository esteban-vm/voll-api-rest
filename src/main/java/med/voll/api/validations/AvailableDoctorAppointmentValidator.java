package med.voll.api.validations;

import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.infra.exceptions.ValidationException;
import med.voll.api.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailableDoctorAppointmentValidator implements IAppointmentValidator {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(AppointmentCreateDTO dto) throws ValidationException {
        var isAlreadyCommitted = repository.existsByDoctorIdAndDateTime(dto.idDoctor(), dto.dateTime());

        if (isAlreadyCommitted) {
            throw new ValidationException("El doctor ya tiene otra consulta reservada para esa misma hora ese mismo día");
        }
    }

}
