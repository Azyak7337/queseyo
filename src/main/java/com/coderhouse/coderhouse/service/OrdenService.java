package com.coderhouse.coderhouse.service;

import com.coderhouse.coderhouse.document.*;
import com.coderhouse.coderhouse.enums.EstadoEnum;
import com.coderhouse.coderhouse.exceptions.NotFoundException;
import com.coderhouse.coderhouse.model.request.OrdenRequest;
import com.coderhouse.coderhouse.model.response.OrdenResponse;
import com.coderhouse.coderhouse.repository.CarritoRepository;
import com.coderhouse.coderhouse.repository.OrdenRepository;
import com.coderhouse.coderhouse.repository.ProductoRepository;
import com.coderhouse.coderhouse.service.interfaces.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OrdenService {

    private final OrdenRepository ordenRepository;

    private final CarritoRepository carritoRepository;

    private final ProductoRepository productoRepository;
    private final SequenceGenerator sequenceGenerator;
    private final UsuarioService userService;

    public Optional<OrdenResponse> getOrderById(Long orderId) {

        Optional<OrdenDocument> order = ordenRepository.findByOrdenNumero(orderId);
        if(order.isPresent()) {
            log.info("La orden de fue encontrada existosamente" + LocalDate.now());
            return Optional.of(OrdenResponse.builder()
                    .ordenNumero(order.get().getOrdenNumero())
                    .email(order.get().getEmail())
                    .items(order.get().getItems())
                    .total(order.get().getTotal())
                    .fechaCompra(order.get().getFechaCompra())
                    .build());
        } else {
            log.error("La orden de compra no fue encontrada" + LocalDate.now());
            throw new NotFoundException("La orden de compra no fue encontrada");
        }
    }

    public void deleteOrder(Long orderId) {
        Optional<OrdenDocument> order = ordenRepository.findByOrdenNumero(orderId);
        if(order.isPresent()) {
            log.info("La orden de compra fue encontrada" + LocalDate.now());
            ordenRepository.deleteById(orderId);
        } else {
            log.error("La orden de compra no fue encontrada" + LocalDate.now());
            throw new NotFoundException("La orden de compra no fue encontrada");
        }
    }

    public OrdenResponse createOrder(OrdenRequest ordenRequest) {
        Optional<CarritoDocument> cart = carritoRepository.findByUsuarioId(ordenRequest.getUserId());
        Optional<UsuarioDocument> user = userService.getUserById(ordenRequest.getUserId());

        if(cart.isPresent() && user.isPresent()) {
            OrdenDocument order = OrdenDocument.builder()
                    .ordenNumero(sequenceGenerator.generateSequence(OrdenDocument.SEQUENCE_NAME))
                    .email(user.get().getEmail())
                    .usuarioId(ordenRequest.getUserId())
                    .estado(EstadoEnum.GENERADA)
                    .items(cart.get().getProductos())
                    .total(getTotalPrice(cart.get().getProductos()))
                    .build();

            ordenRepository.save(order);
            log.info("La orden de compra fue encontrada" + LocalDate.now());
            return OrdenResponse.builder()
                    .ordenNumero(order.getOrdenNumero())
                    .email(order.getEmail())
                    .items(order.getItems())
                    .total(order.getTotal())
                    .build();
        } else {
            log.error("La orden de compra no fue encontrada" + LocalDate.now());
            throw new NotFoundException("No existe un carrito para ese usuario");
        }
    }

    public String purchaseOrder(Long orderId) {
        Optional<OrdenDocument> order = ordenRepository.findByOrdenNumero(orderId);
        if(order.isPresent()) {
            if(order.get().getEstado() == EstadoEnum.VENDIDA) {
                return "Compra ya realizada";
            } else {
                order.get().setEstado(EstadoEnum.VENDIDA);
                order.get().setFechaCompra(LocalDate.now());
                ordenRepository.save(order.get());
                return "Compra realizada exitosamente";
            }
        } else {
            throw new NotFoundException("No existe orden con ese id");
        }
    }
    private Double getTotalPrice(List<CarritoItem> items) {
        Double total = 0.0;

        for(CarritoItem c : items) {
            Optional<ProductoDocument> product = productoRepository.findById(c.getProductoId());
            if (product.isPresent()) {
                total = total + (product.get().getPrecio() * c.getCantidad().doubleValue());
            }
        }

        return total;
    }
}
