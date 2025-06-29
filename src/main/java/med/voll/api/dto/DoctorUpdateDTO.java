package med.voll.api.dto;

public record DoctorUpdateDTO(

        Long id,
        String name,
        String phone,
        AddressDTO address

) {
}
