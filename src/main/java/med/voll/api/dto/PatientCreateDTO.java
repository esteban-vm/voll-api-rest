package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientCreateDTO(

        @NotBlank(message = "El nombre es requerido")
        String name,

        @NotBlank(message = "El correo electrónico es requerido")
        @Email(message = "El correo electrónico debe ser válido")
        String email,

        @NotBlank(message = "El teléfono es requerido")
        String phone,

        @NotBlank(message = "El documento es requerido")
        @Pattern(regexp = "\\d{7,9}", message = "El formato del documento debe ser válido")
        String document,

        @NotNull(message = "Los datos de dirección son requeridos")
        @Valid
        AddressDTO address

) {
}
