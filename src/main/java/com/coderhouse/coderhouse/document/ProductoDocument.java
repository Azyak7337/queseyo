package com.coderhouse.coderhouse.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document("PRODUCTO")
@Data
@Builder
public class ProductoDocument {

    @Id
    private String id;

    private String nombre;

    private Float precio;

    private String categoria;

    private String descripcion;

}
