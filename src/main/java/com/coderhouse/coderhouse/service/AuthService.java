package com.coderhouse.coderhouse.service;

import com.coderhouse.coderhouse.document.UsuarioDocument;
import com.coderhouse.coderhouse.exceptions.AuthErrorException;
import com.coderhouse.coderhouse.exceptions.UserExistsException;
import com.coderhouse.coderhouse.model.request.LoginRequest;
import com.coderhouse.coderhouse.model.request.RegisterRequest;
import com.coderhouse.coderhouse.model.response.TokenResponse;
import com.coderhouse.coderhouse.repository.UsuarioRepository;
import com.coderhouse.coderhouse.security.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
    private final UsuarioRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    public Optional<UsuarioDocument> signupUser(final RegisterRequest register) {

        if(userRepository.existsByUsername(register.getUsername())) {
            log.error("El usuario ya existe" + LocalDate.now());
            throw new UserExistsException("El usuario ya existe");
        };

        if(userRepository.existsByEmail(register.getEmail())) {
            log.error("El usuario ya existe" + LocalDate.now());
            throw new UserExistsException("El usuario ya existe");
        }

        UsuarioDocument user = new UsuarioDocument();
        user.setUsername(register.getUsername());
        user.setName(register.getName());
        user.setEmail(register.getEmail());
        user.setPhoneNumber(register.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        userRepository.save(user);
        log.info("Usuario creado existosamente" + LocalDate.now());
        return Optional.of(user);
    }

    public Optional<TokenResponse> signinUser(final LoginRequest login) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UsuarioDocument user = (UsuarioDocument) authentication.getPrincipal();

        try {
            String jwt = jwtUtils.generateJwtToken(authentication);

            log.info("Usuario autenticado existosamente" + LocalDate.now());
            return Optional.of(new TokenResponse(
                    jwt,
                    user.getId(),
                    user.getUsername(),
                    user.getEmail()
            ));
        } catch(UnsupportedEncodingException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            throw new AuthErrorException("Usuario no autorizado");
        }
    }
}
