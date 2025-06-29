package med.voll.api.dto;

import med.voll.api.enums.MedicalSpecialty;
import med.voll.api.models.Doctor;

public record DoctorListDTO(

        String name,
        String email,
        String document,
        MedicalSpecialty specialty

) {

    public DoctorListDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getDocument(), doctor.getSpecialty());
    }
    
}
