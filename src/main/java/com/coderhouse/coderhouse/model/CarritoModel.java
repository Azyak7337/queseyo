package com.coderhouse.coderhouse.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarritoModel {

    private Integer productoId;

    private String direccionEntrega;
}
