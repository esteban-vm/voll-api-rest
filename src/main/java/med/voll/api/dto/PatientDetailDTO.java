package med.voll.api.dto;

import med.voll.api.models.Address;
import med.voll.api.models.Patient;

public record PatientDetailDTO(

        Long id,
        String name,
        String email,
        String phone,
        String document,
        Address address

) {

    public PatientDetailDTO(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDocument(),
                patient.getAddress()
        );
    }

}
