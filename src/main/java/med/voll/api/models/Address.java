package med.voll.api.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String number;
    private String complement;
    private String neighbor;
    private String postal_code;
    private String city;
    private String state;

    public Address(AddressDTO dto) {
        street = dto.street();
        number = dto.number();
        complement = dto.complement();
        neighbor = dto.neighbor();
        postal_code = dto.postal_code();
        city = dto.city();
        state = dto.state();
    }

}
