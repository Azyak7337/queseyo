package com.coderhouse.coderhouse.service;

import com.coderhouse.coderhouse.document.UsuarioDocument;
import com.coderhouse.coderhouse.exceptions.NotFoundException;
import com.coderhouse.coderhouse.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDocument user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("Usuario no encontrado en la base de datos" + LocalDate.now());
            throw new UsernameNotFoundException("Usuario no encontrado en la base de datos");
        } else {
            log.info("Usuario encontrado" + LocalDate.now());
        }

        return UsuarioDocument.build(user);
    }

    public Optional<UsuarioDocument> getUserById(final String id) {
        Optional<UsuarioDocument> user = userRepository.findById(id);
        if(user.isPresent()) {
            log.info("Usuario encontrado" + LocalDate.now());
            return user;
        } else {
            log.error("Usuario no encontrado en la base de datos" + LocalDate.now());
            throw new NotFoundException("No existe usuario con Id " + id);
        }
    }

    public List<UsuarioDocument> getUsers() {
        return userRepository.findAll();
    }

    public UsuarioDocument updateUser(final UsuarioDocument user, final String userId) {
        Optional<UsuarioDocument> updatedUser = userRepository.findById(userId);

        if(updatedUser.isPresent()) {
            updatedUser.get().setName(user.getName());
            updatedUser.get().setEmail(user.getEmail());
            updatedUser.get().setUsername(user.getUsername());
            updatedUser.get().setPhoneNumber(user.getPhoneNumber());

            userRepository.save(updatedUser.get());
            log.info("Usuario creado exitosamente" + LocalDate.now());
            return updatedUser.get();
        } else {
            log.error("Usuario no encontrado en la base de datos" + LocalDate.now());
            throw new NotFoundException("No existe usuario con Id " + userId);
        }
    }

    public void deleteUser(final String userId) {
        Optional<UsuarioDocument> user = userRepository.findById(userId);

        if(user.isPresent()) {
            log.info("Usuario eliminado correctamente" + LocalDate.now());
            userRepository.deleteById(userId);
        } else {
            log.error("Usuario no encontrado en la base de datos" + LocalDate.now());
            throw new NotFoundException("No existe usuario con ese Id " + userId);
        }
    }
}