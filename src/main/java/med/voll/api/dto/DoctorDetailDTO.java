package med.voll.api.dto;

import med.voll.api.enums.MedicalSpecialty;
import med.voll.api.models.Address;
import med.voll.api.models.Doctor;

public record DoctorDetailDTO(

        Long id,
        String name,
        String email,
        String phone,
        String document,
        MedicalSpecialty specialty,
        Address address

) {

    public DoctorDetailDTO(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getDocument(),
                doctor.getSpecialty(),
                doctor.getAddress()
        );
    }
    
}
