package com.coderhouse.coderhouse.model.response;

import com.coderhouse.coderhouse.document.CarritoItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenResponse {

    private Double total;

    private String email;

    private List<CarritoItem> items;

    private LocalDate fechaCompra;

    private Long ordenNumero;
}
