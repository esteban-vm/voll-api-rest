package med.voll.api.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DoctorCreateDTO;
import med.voll.api.dto.DoctorUpdateDTO;
import med.voll.api.enums.MedicalSpecialty;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active;
    private String name;
    private String email;
    private String phone;
    private String document;

    @Enumerated(EnumType.STRING)
    private MedicalSpecialty specialty;

    @Embedded
    private Address address;

    public Doctor(DoctorCreateDTO dto) {
        id = null;
        active = true;
        name = dto.name();
        email = dto.email();
        phone = dto.phone();
        document = dto.document();
        specialty = dto.specialty();
        address = new Address(dto.address());
    }

    public void update(@Valid DoctorUpdateDTO dto) {
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
