package com.coderhouse.coderhouse.controller;

import com.coderhouse.coderhouse.document.UsuarioDocument;
import com.coderhouse.coderhouse.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@RestController
@AllArgsConstructor
public class UserController {

    private final UsuarioService usuarioService;

    @GetMapping(
            value = "/users",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<UsuarioDocument>> getUsers() {
        List<UsuarioDocument> users = usuarioService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(
            value = "/users/{userId}",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<?> getUserById(@PathVariable(name="userId") String userId) {
        Optional<UsuarioDocument> user = usuarioService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @PutMapping(
            value = "/users/{userId}",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UsuarioDocument> updateUser(
            @Valid
            @PathVariable(name = "userId") String userId,
            @RequestBody UsuarioDocument user) {

        UsuarioDocument updatedUser = usuarioService.updateUser(user, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(
            value = "/users/{userId}",
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(name = "userId") String userId) {
        usuarioService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
