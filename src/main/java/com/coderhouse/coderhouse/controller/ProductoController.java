package com.coderhouse.coderhouse.controller;

import com.coderhouse.coderhouse.model.request.ProductoRequest;
import com.coderhouse.coderhouse.model.response.ProductoResponse;
import com.coderhouse.coderhouse.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping()
    public ResponseEntity<?> crearProducto(
            @RequestBody ProductoRequest producto) {

        productoService.crearProducto(producto);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<ProductoResponse>> buscarProductos() {

        return ResponseEntity.ok(productoService.buscarProductos());

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(
            @PathVariable("id") String id,
            @RequestBody ProductoRequest producto) {

        productoService.actualizarProducto(producto, id);

        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable("id") String id) {

        productoService.borrarProducto(id);

        return ResponseEntity.noContent().build();
    }
}
