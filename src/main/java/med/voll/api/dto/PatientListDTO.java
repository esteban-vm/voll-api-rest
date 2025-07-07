package med.voll.api.dto;

import med.voll.api.models.Patient;

public record PatientListDTO(

        Long id,
        String name,
        String email,
        String document

) {

    public PatientListDTO(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getDocument()
        );
    }

}
