package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorDTO;
import med.voll.api.dto.DoctorListDTO;
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

    @Transactional
    @PostMapping
    public void register(@RequestBody @Valid DoctorDTO doctor) {
        repository.save(new Doctor(doctor));
    }

    @GetMapping
    public Page<DoctorListDTO> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository.findAll(pageable).map(DoctorListDTO::new);
    }

}
