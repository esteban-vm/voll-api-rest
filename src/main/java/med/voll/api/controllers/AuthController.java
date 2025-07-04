package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dto.AuthLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthLoginDTO login) {
        var token = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        var auth = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }

}
