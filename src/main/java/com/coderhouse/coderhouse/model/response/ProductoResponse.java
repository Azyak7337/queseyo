package com.coderhouse.coderhouse.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoResponse {

    private String id;

    private String nombre;

    private Float precio;

    private String categoria;

    private String descripcion;
}
