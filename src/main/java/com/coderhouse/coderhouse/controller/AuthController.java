package com.coderhouse.coderhouse.controller;

import com.coderhouse.coderhouse.document.UsuarioDocument;
import com.coderhouse.coderhouse.model.request.LoginRequest;
import com.coderhouse.coderhouse.model.request.RegisterRequest;
import com.coderhouse.coderhouse.model.response.TokenResponse;
import com.coderhouse.coderhouse.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value="/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest register) {

        Optional<UsuarioDocument> registeredUser = authService.signupUser(register);

        if(registeredUser.isPresent()) {
            return ResponseEntity.ok().body("Usuario registrado correctamente");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value="/signin")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest login) throws UnsupportedEncodingException {

        Optional<TokenResponse> loginUser = authService.signinUser(login);

        if(loginUser.isPresent()) {
            return ResponseEntity.ok(loginUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
