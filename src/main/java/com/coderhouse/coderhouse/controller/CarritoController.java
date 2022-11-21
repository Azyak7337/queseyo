package com.coderhouse.coderhouse.controller;

import com.coderhouse.coderhouse.document.CarritoDocument;
import com.coderhouse.coderhouse.model.request.CarritoRequest;
import com.coderhouse.coderhouse.model.response.CarritoResponse;
import com.coderhouse.coderhouse.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @GetMapping()
    public ResponseEntity<Optional<CarritoDocument>> findCarritoByEmail(@RequestParam String email) {

        return ResponseEntity.ok(carritoService.findCarritoByEmail(email));
    }

    @PostMapping()
    public ResponseEntity<CarritoDocument> crearCarrito(@RequestBody CarritoRequest carritoRequest) {

        return ResponseEntity.ok(carritoService.crearCarrito(carritoRequest));
    }

    @PutMapping()
    public ResponseEntity<CarritoDocument> modificarCarrito(@RequestBody CarritoRequest carritoRequest) {

        return ResponseEntity.ok(carritoService.modificarCarrito(carritoRequest));
    }

    @PostMapping("/item")
    public ResponseEntity<CarritoDocument> agregarItem(@RequestBody CarritoRequest carritoRequest) {

        return ResponseEntity.ok(carritoService.agregarItem(carritoRequest));
    }

    @DeleteMapping()
    public ResponseEntity<?> borrarCarrito(String id) {

        carritoService.borrarCarrito(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/item")
    public ResponseEntity<CarritoDocument> borrarItem(@RequestBody CarritoRequest carritoRequest) {

        return ResponseEntity.ok(carritoService.eliminarItem(carritoRequest));
    }
}
