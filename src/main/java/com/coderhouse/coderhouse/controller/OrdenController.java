package com.coderhouse.coderhouse.controller;

import com.coderhouse.coderhouse.model.request.OrdenRequest;
import com.coderhouse.coderhouse.model.response.OrdenResponse;
import com.coderhouse.coderhouse.service.OrdenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/orden")
@AllArgsConstructor
public class OrdenController {
    private final OrdenService orderService;

    @GetMapping(value="/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable(name="orderId") Long orderId) {
        Optional<OrdenResponse> order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping(value="/")
    public ResponseEntity<OrdenResponse> createOrder(@RequestBody OrdenRequest order) {
        OrdenResponse createOrder = orderService.createOrder(order);
        return ResponseEntity.created(URI.create(("/order"))).body(createOrder);
    }

    @PostMapping(value="/{orderId}")
    public ResponseEntity<?> purchaseOrder(@PathVariable(name="orderId") Long orderId) {
        String message = orderService.purchaseOrder(orderId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping(value="/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name="orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}

