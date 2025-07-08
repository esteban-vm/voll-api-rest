package med.voll.api.validations;

import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.infra.exceptions.ValidationException;

public interface IAppointmentValidator {

    void validate(AppointmentCreateDTO dto) throws ValidationException;

}
