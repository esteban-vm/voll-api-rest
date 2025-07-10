package med.voll.api.repositories;

import jakarta.persistence.EntityManager;
import med.voll.api.dto.AddressDTO;
import med.voll.api.dto.DoctorCreateDTO;
import med.voll.api.dto.PatientCreateDTO;
import med.voll.api.enums.MedicalSpecialty;
import med.voll.api.models.Appointment;
import med.voll.api.models.Doctor;
import med.voll.api.models.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private EntityManager manager;

    @Test
    @DisplayName("Debería devolver null cuando el médico buscado exista pero no esté disponible en la fecha")
    void chooseRandomDoctorAvailableOnDateScenario1() {
        // given or arrange
        var doctor = registerDoctor("Doctor 1", "doctor1email@ejemplo.com", "12345", MedicalSpecialty.CARDIOLOGIA);
        var patient = registerPatient("Paciente 1", "paciente1email@ejemplo.com", "54321");
        var nextMondayAt10 = getAppointmentDateTime();
        registerAppointment(doctor, patient, nextMondayAt10);
        // when or act
        var availableDoctor = repository.chooseRandomDoctorAvailableOnDate(MedicalSpecialty.CARDIOLOGIA, nextMondayAt10);
        // then or assert
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Debería devolver el médico cuando sí esté disponible en la fecha")
    void chooseRandomDoctorAvailableOnDateScenario2() {
        // given or arrange
        var doctor = registerDoctor("Doctor 2", "doctor2email@ejemplo.com", "54321", MedicalSpecialty.DERMATOLOGIA);
        var nextMondayAt10 = getAppointmentDateTime();
        // when or act
        var availableDoctor = repository.chooseRandomDoctorAvailableOnDate(MedicalSpecialty.DERMATOLOGIA, nextMondayAt10);
        // then or assert
        assertThat(availableDoctor).isEqualTo(doctor);
    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime dateTime) {
        manager.persist(new Appointment(null, doctor, patient, dateTime, null));
    }

    private Doctor registerDoctor(String name, String email, String document, MedicalSpecialty specialty) {
        var doctor = new Doctor(getDoctorData(name, email, document, specialty));
        manager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String document) {
        var patient = new Patient(getPatientData(name, email, document));
        manager.persist(patient);
        return patient;
    }

    private DoctorCreateDTO getDoctorData(String name, String email, String document, MedicalSpecialty specialty) {
        return new DoctorCreateDTO(name, email, "4541256", document, specialty, getAddressData());
    }

    private PatientCreateDTO getPatientData(String name, String email, String document) {
        return new PatientCreateDTO(name, email, "1234567", document, getAddressData());
    }

    private AddressDTO getAddressData() {
        return new AddressDTO(
                "calle x",
                "12",
                "complemento",
                "distrito y",
                "1234",
                "ciudad z",
                "estado a");
    }

    private LocalDateTime getAppointmentDateTime() {
        return LocalDate
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
    }

}
