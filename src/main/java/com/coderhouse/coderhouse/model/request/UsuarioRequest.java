package com.coderhouse.coderhouse.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRequest {

    private String userName;

    private String name;

    private String phone;

    private String password;

    private String email;
}
