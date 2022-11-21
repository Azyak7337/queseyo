package com.coderhouse.coderhouse.controller;

import com.coderhouse.coderhouse.document.UsuarioDocument;
import com.coderhouse.coderhouse.model.request.UsuarioRequest;
import com.coderhouse.coderhouse.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService userService;

//    @PostMapping("/login")
//    public UsuarioDocument login(@RequestParam String email, @RequestParam String password) {
//        return userService.login(email, password);
//    }

    @PostMapping("/signup")
    public UsuarioDocument signup(@RequestBody UsuarioRequest userRequest) {
        return userService.signUp(userRequest);
    }
}