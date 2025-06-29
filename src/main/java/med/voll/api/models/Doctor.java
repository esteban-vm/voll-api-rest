package med.voll.api.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DoctorRegisterDTO;
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

    private String name;
    private String email;
    private String phone;
    private String document;

    @Enumerated(EnumType.STRING)
    private MedicalSpecialty specialty;

    @Embedded
    private Address address;

    public Doctor(DoctorRegisterDTO dto) {
        id = null;
        name = dto.name();
        email = dto.email();
        phone = dto.phone();
        document = dto.document();
        specialty = dto.specialty();
        address = new Address(dto.address());
    }

    public void updateInfo(@Valid DoctorUpdateDTO dto) {
        if (dto.name() != null) {
            name = dto.name();
        }

        if (dto.phone() != null) {
            phone = dto.phone();
        }

        if (dto.address() != null) {
            address.updateInfo(dto.address());
        }
    }
}
