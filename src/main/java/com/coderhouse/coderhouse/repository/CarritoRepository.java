package com.coderhouse.coderhouse.repository;

import com.coderhouse.coderhouse.document.CarritoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends MongoRepository<CarritoDocument, Long> {
}
