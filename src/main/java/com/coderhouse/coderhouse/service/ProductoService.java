package com.coderhouse.coderhouse.service;

import com.coderhouse.coderhouse.document.ProductoDocument;
import com.coderhouse.coderhouse.model.ProductoRequest;
import com.coderhouse.coderhouse.model.ProductoResponse;
import com.coderhouse.coderhouse.model.mapper.ProductoMapper;
import com.coderhouse.coderhouse.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void borrarProducto(Long id) {

        productoRepository.deleteById(id);

    }
}
