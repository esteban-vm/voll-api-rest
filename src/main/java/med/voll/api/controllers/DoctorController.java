package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorDTO;
import med.voll.api.models.Doctor;
import med.voll.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
