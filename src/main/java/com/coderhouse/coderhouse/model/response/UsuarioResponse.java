package com.coderhouse.coderhouse.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResponse {
    private String userName;
    private String email;
    private String token;
}
