package com.coderhouse.coderhouse.document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarritoItem {

    private String productoId;

    private Integer cantidad;

    private String nombre;
}
