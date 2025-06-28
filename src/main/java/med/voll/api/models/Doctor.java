package med.voll.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DoctorDTO;
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

    public Doctor(DoctorDTO doctor) {
        id = null;
        name = doctor.name();
        email = doctor.email();
        phone = doctor.phone();
        document = doctor.document();
        specialty = doctor.specialty();
        address = new Address(doctor.address());
    }

}
