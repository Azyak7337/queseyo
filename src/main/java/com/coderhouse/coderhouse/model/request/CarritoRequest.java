package com.coderhouse.coderhouse.model.request;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarritoRequest {

    private String productoId;

    private String email;

    private Integer cantidad;
}
