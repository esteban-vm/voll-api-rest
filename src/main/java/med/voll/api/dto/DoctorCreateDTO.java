package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.enums.MedicalSpecialty;

public record DoctorCreateDTO(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{7,9}")
        String document,

        @NotNull
        MedicalSpecialty specialty,

        @NotNull
        @Valid
        AddressDTO address

) {
}
