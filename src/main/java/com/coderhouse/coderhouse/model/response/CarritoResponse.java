package com.coderhouse.coderhouse.model.response;

import com.coderhouse.coderhouse.document.CarritoItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarritoResponse {

    private String id;

    private List<CarritoItem> productos;

}
