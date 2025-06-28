package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(

        @NotBlank
        String street,

        String number,
        String complement,

        @NotBlank
        String neighbor,

        @NotBlank
        @Pattern(regexp = "\\d{4}")
        String postal_code,

        @NotBlank
        String city,

        @NotBlank
        String state

) {
}
