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

    public Address(AddressDTO address) {
        street = address.street();
        number = address.number();
        complement = address.complement();
        neighbor = address.neighbor();
        postal_code = address.postal_code();
        city = address.city();
        state = address.state();
    }

}
