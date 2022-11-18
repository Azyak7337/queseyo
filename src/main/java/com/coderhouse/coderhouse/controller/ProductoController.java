package com.coderhouse.coderhouse.controller;

import com.coderhouse.coderhouse.document.ProductoDocument;
import com.coderhouse.coderhouse.model.ProductoRequest;
import com.coderhouse.coderhouse.model.ProductoResponse;
import com.coderhouse.coderhouse.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(
            @RequestBody ProductoRequest producto) {

        productoService.crearProducto(producto);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<ProductoResponse>> buscarProductos() {

        return ResponseEntity.ok(productoService.buscarProductos());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable("id") Long id) {

        productoService.borrarProducto(id);

        return ResponseEntity.noContent().build();
    }
}
