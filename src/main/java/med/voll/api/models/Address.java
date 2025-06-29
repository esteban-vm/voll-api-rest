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

    public void update(AddressDTO dto) {
        if (dto.street() != null) {
            street = dto.street();
        }

        if (dto.number() != null) {
            number = dto.number();
        }

        if (dto.complement() != null) {
            complement = dto.complement();
        }

        if (dto.neighbor() != null) {
            neighbor = dto.neighbor();
        }

        if (dto.postal_code() != null) {
            postal_code = dto.postal_code();
        }

        if (dto.city() != null) {
            city = dto.city();
        }

        if (dto.state() != null) {
            state = dto.state();
        }
    }

}
