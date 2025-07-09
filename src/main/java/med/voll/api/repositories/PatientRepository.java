package med.voll.api.repositories;

import med.voll.api.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAllByActiveTrue(Pageable pageable);

    @Query(value = """
            select p.active
            from Patient p
            where
            p.id = :idPatient
            """)
    Boolean findActiveById(Long idPatient);

}
