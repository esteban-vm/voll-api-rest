package med.voll.api.services;

import med.voll.api.dto.AppointmentCreateDTO;
import med.voll.api.dto.AppointmentDeleteDTO;
import med.voll.api.infra.exceptions.ValidationException;
import med.voll.api.models.Appointment;
import med.voll.api.models.Doctor;
import med.voll.api.repositories.AppointmentRepository;
import med.voll.api.repositories.DoctorRepository;
import med.voll.api.repositories.PatientRepository;
import med.voll.api.validations.IAppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<IAppointmentValidator> validators;

    private Doctor chooseDoctor(AppointmentCreateDTO dto) {
        if (dto.idDoctor() != null) {
            return doctorRepository.getReferenceById(dto.idDoctor());
        }

        if (dto.specialty() == null) {
            throw new ValidationException("La especialidad no existe");
        }

        return doctorRepository.chooseRandomDoctorAvailableOnDate(dto.specialty(), dto.dateTime());
    }

    public void bookAppointment(AppointmentCreateDTO dto) throws ValidationException {
        if (dto.idDoctor() != null && !doctorRepository.existsById(dto.idDoctor())) {
            throw new ValidationException("El médico no existe");
        }

        if (!patientRepository.existsById(dto.idPatient())) {
            throw new ValidationException("El paciente no existe");
        }

        // Validations
        validators.forEach(validator -> validator.validate(dto));

        var doctor = chooseDoctor(dto);
        var patient = patientRepository.findById(dto.idPatient()).get();
        var appointment = new Appointment(null, doctor, patient, dto.dateTime(), null);
        appointmentRepository.save(appointment);
    }

    public void cancelAppointment(AppointmentDeleteDTO dto) {
        if (!appointmentRepository.existsById(dto.idAppointment())) {
            throw new ValidationException("La consulta no existe");
        }

        var appointment = appointmentRepository.getReferenceById(dto.idAppointment());
        appointment.cancel(dto.reason());
    }

}
