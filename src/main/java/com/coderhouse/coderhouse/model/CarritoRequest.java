package com.coderhouse.coderhouse.model;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarritoRequest {

    private Long productoId;

    private String direccionEntrega;
}
