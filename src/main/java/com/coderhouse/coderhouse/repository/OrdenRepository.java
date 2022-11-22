package com.coderhouse.coderhouse.repository;

import com.coderhouse.coderhouse.document.OrdenDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrdenRepository extends MongoRepository<OrdenDocument, Long> {

    Optional<OrdenDocument> findByOrdenNumero(Long orderNumber);
}
