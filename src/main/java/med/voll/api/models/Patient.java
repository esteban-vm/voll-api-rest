package med.voll.api.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.PatientCreateDTO;
import med.voll.api.dto.PatientUpdateDTO;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active;
    private String name;
    private String email;
    private String phone;
    private String document;

    @Embedded
    private Address address;

    public Patient(PatientCreateDTO dto) {
        id = null;
        active = true;
        name = dto.name();
        email = dto.email();
        phone = dto.phone();
        document = dto.document();
        address = new Address(dto.address());
    }

    public void update(@Valid PatientUpdateDTO dto) {
        if (dto.name() != null) {
            name = dto.name();
        }

        if (dto.phone() != null) {
            phone = dto.phone();
        }

        if (dto.address() != null) {
            address.update(dto.address());
        }
    }

    public void delete() {
        active = false;
    }

}
