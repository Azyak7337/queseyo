package com.coderhouse.coderhouse.document;

import com.coderhouse.coderhouse.document.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="user")
public class UsuarioDocument implements UserDetails {
        @Id
        private String id;
        @NotBlank(message = "El campo nombre de usuario es obligatorio")
        private String username;

        @NotBlank(message = "El campo nombre es obligatorio")
        private String name;

        private String phoneNumber;
        @NotBlank(message = "El campo email es obligatorio")
        @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Debe ser un email válido")
        private String email;

        private Collection<Role> roles = new ArrayList<>();
        @JsonIgnore
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public UsuarioDocument(String id, String username, String email, String password,
                    Collection<? extends GrantedAuthority> authorities) {
                this.id = id;
                this.username = username;
                this.email = email;
                this.password = password;
                this.authorities = authorities;
        }

        public static UsuarioDocument build(UsuarioDocument user) {
                List<GrantedAuthority> authorities = user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                        .collect(Collectors.toList());

                return new UsuarioDocument(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        authorities);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
        }

        public String getId() {
                return id;
        }

        public String getEmail() {
                return email;
        }

        @Override
        public String getPassword() {
                return password;
        }

        @Override
        public String getUsername() {
                return username;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o)
                        return true;
                if (o == null || getClass() != o.getClass())
                        return false;
                UsuarioDocument usuario = (UsuarioDocument) o;
                return Objects.equals(id, usuario.id);
        }
}

