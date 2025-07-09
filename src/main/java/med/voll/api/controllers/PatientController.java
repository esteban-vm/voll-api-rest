package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.dto.PatientCreateDTO;
import med.voll.api.dto.PatientDetailDTO;
import med.voll.api.dto.PatientListDTO;
import med.voll.api.dto.PatientUpdateDTO;
import med.voll.api.models.Patient;
import med.voll.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @GetMapping
    public ResponseEntity<Page<PatientListDTO>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(PatientListDTO::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PatientDetailDTO> create(@RequestBody @Valid PatientCreateDTO dto, UriComponentsBuilder builder) {
        var patient = new Patient(dto);
        repository.save(patient);
        var uri = builder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailDTO(patient));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<PatientDetailDTO> update(@RequestBody @Valid PatientUpdateDTO dto) {
        var patient = repository.getReferenceById(dto.id());
        patient.update(dto);
        return ResponseEntity.ok(new PatientDetailDTO(patient));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDetailDTO> delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
        return ResponseEntity.noContent().build();
    }

}
