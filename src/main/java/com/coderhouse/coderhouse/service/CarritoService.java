package com.coderhouse.coderhouse.service;

import com.coderhouse.coderhouse.document.CarritoDocument;
import com.coderhouse.coderhouse.document.CarritoItem;
import com.coderhouse.coderhouse.document.ProductoDocument;
import com.coderhouse.coderhouse.model.mapper.CarritoMapper;
import com.coderhouse.coderhouse.model.request.CarritoRequest;
import com.coderhouse.coderhouse.model.response.CarritoResponse;
import com.coderhouse.coderhouse.repository.CarritoRepository;
import com.coderhouse.coderhouse.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    private final CarritoMapper carritoMapper;

    public Optional<CarritoDocument> findCarritoByEmail(String email) {

        Optional<CarritoDocument> carrito = carritoRepository.findByEmail(email);

        return carrito;
    }

    public CarritoDocument crearCarrito(CarritoRequest carritoRequest) {

        ProductoDocument producto = productoRepository.findById(carritoRequest.getProductoId())
                .orElseThrow(() -> new IllegalArgumentException("NO EXISTE EL PRODUCTO"));

        Optional<CarritoDocument> carritoCheck = carritoRepository.findByEmail(carritoRequest.getEmail());

        if(carritoCheck.isPresent()) {
            throw new IllegalArgumentException("YA EXISTE UN CARRITO PARA EL CORREO");
        }

        CarritoItem carritoItem = CarritoItem.builder()
                .productoId(producto.getId().toString())
                .cantidad(carritoRequest.getCantidad())
                .build();

        CarritoDocument carrito = carritoRepository.save(CarritoDocument.builder()
                .email(carritoRequest.getEmail())
                .fechaAdicion(LocalDateTime.now())
                        .productos(List.of(carritoItem))
                .build());


        return carrito;
    }

    public CarritoDocument modificarCarrito(CarritoRequest carritoRequest) {

        ProductoDocument producto = productoRepository.findById(carritoRequest.getProductoId())
                .orElseThrow(() -> new IllegalArgumentException("NO EXISTE EL PRODUCTO"));

        CarritoDocument carrito = carritoRepository.findByEmail(carritoRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("NO EXISTE EL CARRITO"));

        CarritoItem carritoItem = CarritoItem.builder()
                .productoId(carritoRequest.getProductoId())
                .cantidad(carritoRequest.getCantidad())
                .build();

        CarritoItem carritoItemCheck = buscarCarritoItemByProductoId(carritoRequest.getProductoId(),
                carrito.getProductos());

        if(carritoItem.getCantidad() == 0) {
            carrito.getProductos().remove(carritoItemCheck);
            carritoRepository.save(carrito);
            return carrito;
        }

        carrito.getProductos().remove(carritoItemCheck);
        carrito.getProductos().add(carritoItem);

        return carrito;
    }

    public void borrarCarrito(String id) {

        carritoRepository.deleteById(id);
    }

    public CarritoDocument agregarItem(CarritoRequest carritoRequest) {

        ProductoDocument producto = productoRepository.findById(carritoRequest.getProductoId())
                .orElseThrow(() -> new IllegalArgumentException("NO EXISTE EL PRODUCTO"));

        CarritoDocument carrito = carritoRepository.findByEmail(carritoRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("NO EXISTE EL CARRITO"));

        CarritoItem carritoItem = CarritoItem.builder()
                .productoId(carritoRequest.getProductoId())
                .cantidad(carritoRequest.getCantidad())
                .build();

        CarritoItem carritoItemCheck = buscarCarritoItemByProductoId(carritoRequest.getProductoId(),
                carrito.getProductos());

        if(carritoItemCheck != null) {
            throw new IllegalStateException("EL PRODUCTO YA ESTA EN EL CARRITO");
        }

        if(carrito.getProductos().isEmpty()) {
            carrito.setProductos(List.of(carritoItem));
            carritoRepository.save(carrito);
            return carrito;
        }

        carrito.getProductos().add(carritoItem);

        carritoRepository.save(carrito);

        return carrito;
    }

    public CarritoDocument eliminarItem(CarritoRequest carritoRequest) {

        CarritoDocument carrito = carritoRepository.findByEmail(carritoRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("NO EXISTE EL CARRITO"));

        CarritoItem carritoItem = CarritoItem.builder()
                .productoId(carritoRequest.getProductoId())
                .cantidad(carritoRequest.getCantidad())
                .build();

        CarritoItem carritoItemCheck = buscarCarritoItemByProductoId(carritoRequest.getProductoId(),
                carrito.getProductos());

        if(carritoItemCheck == null) {
            throw new IllegalArgumentException("EL ITEM NO ESTA EN EL CARRITO");
        }

        carrito.getProductos().remove(carritoItem);

        return carrito;
    }

    private CarritoItem buscarCarritoItemByProductoId(String productoId, List<CarritoItem> productos) {
        CarritoItem itemCheck = null;
        for (CarritoItem carritoItem : productos) {
            if (carritoItem.getProductoId().equals(productoId)) {
                itemCheck = carritoItem;
            }
        }
        return itemCheck;
    }
}
