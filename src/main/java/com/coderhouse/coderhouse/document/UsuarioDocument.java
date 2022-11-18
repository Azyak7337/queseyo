package com.coderhouse.coderhouse.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("USUARIO")
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDocument {

        @Id
        private String id;

        private String name;

        private String phone;

        private String email;

        private String userName;

        private String password;
    }
