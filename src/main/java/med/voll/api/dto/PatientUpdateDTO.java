package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record PatientUpdateDTO(

        @NotNull Long id,
        String name,
        String phone,
        AddressDTO address

) {
}
