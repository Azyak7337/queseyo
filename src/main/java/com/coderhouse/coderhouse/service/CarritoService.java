package com.coderhouse.coderhouse.service;

import com.coderhouse.coderhouse.document.CarritoDocument;
import com.coderhouse.coderhouse.document.ProductoDocument;
import com.coderhouse.coderhouse.model.CarritoModel;
import com.coderhouse.coderhouse.model.CarritoRequest;
import com.coderhouse.coderhouse.repository.CarritoRepository;
import com.coderhouse.coderhouse.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    public CarritoDocument addProduct(CarritoRequest carritoRequest) {

        ProductoDocument producto = productoRepository.findById(carritoRequest.getProductoId())
                .orElseThrow(() -> new IllegalArgumentException("NO EXISTE EL PRODUCTO"));

        CarritoDocument carrito = new CarritoDocument();
        carrito.setDireccionEntrega(carritoRequest.getDireccionEntrega());
        carrito.setFechaAdicion(LocalDateTime.now());
        carrito.setProductos(List.of(producto));


        return carrito;
    }
}
