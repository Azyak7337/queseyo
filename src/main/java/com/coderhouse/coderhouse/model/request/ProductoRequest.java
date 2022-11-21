package com.coderhouse.coderhouse.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoRequest {

    private String nombre;

    private Float precio;

    private String categoria;

    private String descripcion;
}
