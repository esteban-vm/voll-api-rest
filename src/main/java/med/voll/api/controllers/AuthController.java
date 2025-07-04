package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.dto.AuthLoginDTO;
import med.voll.api.dto.JWTTokenDTO;
import med.voll.api.models.User;
import med.voll.api.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private TokenService service;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthLoginDTO login) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(login.username(), login.password());
            var auth = manager.authenticate(authToken);
            var jwtToken = service.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new JWTTokenDTO(jwtToken));
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
