package med.voll.api.dto;

public record AddressDTO(
        String street,
        String number,
        String complement,
        String neighbor,
        String postal_code,
        String city,
        String state
) {
}
