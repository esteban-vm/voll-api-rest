package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorCreateDTO;
import med.voll.api.dto.DoctorDetailDTO;
import med.voll.api.dto.DoctorListDTO;
import med.voll.api.dto.DoctorUpdateDTO;
import med.voll.api.models.Doctor;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public ResponseEntity<Page<DoctorListDTO>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(DoctorListDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailDTO> getOne(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailDTO(doctor));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<DoctorDetailDTO> create(@RequestBody @Valid DoctorCreateDTO dto, UriComponentsBuilder builder) {
        var doctor = new Doctor(dto);
        repository.save(doctor);
        var uri = builder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailDTO(doctor));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DoctorDetailDTO> update(@RequestBody @Valid DoctorUpdateDTO dto) {
        var doctor = repository.getReferenceById(dto.id());
        doctor.update(dto);
        return ResponseEntity.ok(new DoctorDetailDTO(doctor));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorDetailDTO> delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }

}
