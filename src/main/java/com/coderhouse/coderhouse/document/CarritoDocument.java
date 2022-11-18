package com.coderhouse.coderhouse.document;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("CARRITO")
@Getter
@Setter
public class CarritoDocument {

    @Id
    private Long id;

    private List<ProductoDocument> productos;

    private LocalDateTime fechaAdicion;

    private String direccionEntrega;
}
