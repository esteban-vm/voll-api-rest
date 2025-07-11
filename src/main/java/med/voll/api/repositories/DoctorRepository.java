package med.voll.api.repositories;

import med.voll.api.enums.MedicalSpecialty;
import med.voll.api.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query(value = """
            select d
            from Doctor d
            where
            d.active = true
            and
            d.specialty = :specialty
            and
            d.id not in(
                select a.doctor.id from Appointment a
                where
                a.dateTime = :dateTime
            )
            order by rand()
            limit 1
            """)
    Doctor chooseRandomDoctorAvailableOnDate(MedicalSpecialty specialty, LocalDateTime dateTime);

    @Query(value = """
            select d.active
            from Doctor d
            where
            d.id = :idDoctor
            """)
    Boolean findActiveById(Long idDoctor);

}
