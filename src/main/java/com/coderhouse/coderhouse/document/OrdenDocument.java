package com.coderhouse.coderhouse.document;

import com.coderhouse.coderhouse.enums.EstadoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection="order")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDocument {

    @Transient
    public static final String SEQUENCE_NAME = "orderNumber";

    @Id
    private Long ordenNumero;

    private String usuarioId;

    private List<CarritoItem> items;

    private Double total;

    private LocalDate fechaCompra;

    private EstadoEnum estado;

    private String email;
}
