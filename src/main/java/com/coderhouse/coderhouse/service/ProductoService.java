package com.coderhouse.coderhouse.service;

import com.coderhouse.coderhouse.document.ProductoDocument;
import com.coderhouse.coderhouse.model.request.ProductoRequest;
import com.coderhouse.coderhouse.model.response.ProductoResponse;
import com.coderhouse.coderhouse.model.mapper.ProductoMapper;
import com.coderhouse.coderhouse.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoDocument crearProducto(ProductoRequest productoRequest) {

        ProductoDocument document = productoRepository.save(ProductoDocument.builder()
                .nombre(productoRequest.getNombre())
                .precio(productoRequest.getPrecio())
                .categoria(productoRequest.getCategoria())
                .descripcion(productoRequest.getDescripcion())
                .build());


        return document;

    }

    public List<ProductoResponse> buscarProductos() {

        List<ProductoResponse> productos = productoRepository.findAll()
                .parallelStream()
                .map(productoMapper::toFullModel)
                .collect(Collectors.toList());

        return productos;
    }

    public ProductoDocument actualizarProducto(ProductoRequest productoRequest, String id) {

        ProductoDocument producto = productoRepository.findById(id)
                .orElseThrow( () -> new NoSuchElementException("NO EXISTE EL PRODUCTO"));

        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setNombre(producto.getNombre());
        producto.setCategoria(producto.getCategoria());

        productoRepository.save(producto);

        return producto;
    }

    public void borrarProducto(String id) {

        productoRepository.deleteById(id);

    }
}
