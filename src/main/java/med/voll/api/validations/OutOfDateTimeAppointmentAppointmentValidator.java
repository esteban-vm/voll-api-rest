package med.voll.api.validations;

import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.infra.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class OutOfDateTimeAppointmentAppointmentValidator implements IAppointmentValidator {

    @Override
    public void validate(AppointmentCreateDTO dto) throws ValidationException {
        var dateTime = dto.dateTime();
        var isSunday = dateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isBeforeOpening = dateTime.getHour() < 7;
        var isAfterClosing = dateTime.getHour() > 18;

        if (isSunday || isBeforeOpening || isAfterClosing) {
            throw new ValidationException("Fecha y/u hora fuera de atención de la clínica");
        }
    }

}
