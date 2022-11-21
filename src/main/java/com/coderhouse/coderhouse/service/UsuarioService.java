package com.coderhouse.coderhouse.service;

import com.coderhouse.coderhouse.document.UsuarioDocument;
import com.coderhouse.coderhouse.model.request.UsuarioRequest;
import com.coderhouse.coderhouse.model.response.UsuarioResponse;
import com.coderhouse.coderhouse.repository.UsuarioRepository;
import com.coderhouse.coderhouse.security.JwtProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

//    public UserResponse login(String email, String password) {
//        UserResponse response = null;
//        try {
//            var token = jwtProvider.getJwToken(email);
//            var dataFromCache = cacheClient.recover(email, UserDocument.class);
//            if (Objects.nonNull(dataFromCache)) {
////                var dataFromCacheResponse = UserBuilder.documentToResponse(dataFromCache);
////                dataFromCacheResponse.setToken(token);
////                response = dataFromCacheResponse;
//                response = UserResponse.builder().email(email).token(token).build();
//            }
//            UserDocument userDocumentFound = userRepository.findByEmail(email);
//            if (userDocumentFound == null || !passwordEncoder.matches(password, userDocumentFound.getPassword())) {//!userDocumentFound.getPassword().equals(password)) {
//                throw new ApiRestException(email, "Email o contraseña incorrectos");
//            }
//            saveUserInCache(userDocumentFound);
//            response = UserResponse.builder().email(email).token(token).build();
//        } catch (JsonProcessingException e) {
//            log.error("Erorr JsonProcessingException", e);
//        }
//
//
//        return response;
//    }

    public UsuarioDocument signUp(UsuarioRequest request) {

//            validateUser(request);

            request.setPassword(passwordEncoder.encode(request.getPassword()));

            UsuarioDocument usuario = UsuarioDocument.builder()
                    .email(request.getEmail())
                    .name(request.getName())
                    .password(request.getPassword())
                    .userName(request.getUserName())
                    .phone(request.getPhone())
                    .build();

            usuarioRepository.save(usuario);

        return usuario;
    }

    void validateUser(UsuarioRequest request) {
        UsuarioDocument usuario = usuarioRepository.findByUserName(request.getUserName());
        if (usuario != null) {
            throw new IllegalStateException("El usuario ya existe");
        }
        usuario = usuarioRepository.findByEmail(request.getEmail());
        if (usuario != null) {
            throw new IllegalStateException("El usuario ya existe");
        }
    }
}
