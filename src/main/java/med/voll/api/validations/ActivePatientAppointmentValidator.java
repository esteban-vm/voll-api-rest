package med.voll.api.validations;

import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.infra.exceptions.ValidationException;
import med.voll.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientAppointmentValidator implements IAppointmentValidator {

    @Autowired
    private PatientRepository repository;

    @Override
    public void validate(AppointmentCreateDTO dto) throws ValidationException {
        var isActivePatient = repository.findActiveById(dto.idPatient());

        if (!isActivePatient) {
            throw new ValidationException("El paciente no está activo");
        }
    }

}
