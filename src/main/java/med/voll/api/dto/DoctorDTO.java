package med.voll.api.dto;

import med.voll.api.enums.MedicalSpecialty;

public record DoctorDTO(
        String name,
        String email,
        String document,
        MedicalSpecialty specialty,
        AddressDTO address
) {
}
