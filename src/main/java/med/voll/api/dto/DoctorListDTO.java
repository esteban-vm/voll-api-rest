package med.voll.api.dto;

import med.voll.api.enums.MedicalSpecialty;
import med.voll.api.models.Doctor;

public record DoctorListDTO(

        Long id,
        String name,
        String email,
        String document,
        MedicalSpecialty specialty

) {

    public DoctorListDTO(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getDocument(),
                doctor.getSpecialty()
        );
    }

}
