package med.voll.api.validations;

import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.infra.exceptions.ValidationException;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ActiveDoctorAppointmentValidator implements IAppointmentValidator {

    @Autowired
    private DoctorRepository repository;

    @Override
    public void validate(AppointmentCreateDTO dto) throws ValidationException {
        if (dto.idDoctor() == null) return;
        var isActiveDoctor = repository.findActiveById(dto.idDoctor());

        if (!isActiveDoctor) {
            throw new ValidationException("El doctor no está activo");
        }
    }

}
