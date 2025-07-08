package med.voll.api.validations;

import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.infra.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimeInAdvanceAppointmentAppointmentValidator implements IAppointmentValidator {

    @Override
    public void validate(AppointmentCreateDTO dto) throws ValidationException {
        var dateTime = dto.dateTime();
        var currentDateTime = LocalDateTime.now();
        var differenceInMinutes = Duration.between(currentDateTime, dateTime).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidationException("Favor reservar con un mínimo de 30 minutos de antelación");
        }
    }

}
