package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorListDTO;
import med.voll.api.dto.DoctorRegisterDTO;
import med.voll.api.dto.DoctorUpdateDTO;
import med.voll.api.models.Doctor;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public Page<DoctorListDTO> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(DoctorListDTO::new);
    }

    @Transactional
    @PostMapping
    public void create(@RequestBody @Valid DoctorRegisterDTO dto) {
        repository.save(new Doctor(dto));
    }

    @Transactional
    @PutMapping
    public void update(@RequestBody @Valid DoctorUpdateDTO dto) {
        var doctor = repository.getReferenceById(dto.id());
        doctor.update(dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
    }

}
