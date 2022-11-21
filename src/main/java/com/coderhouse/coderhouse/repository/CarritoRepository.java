package com.coderhouse.coderhouse.repository;

import com.coderhouse.coderhouse.document.CarritoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends MongoRepository<CarritoDocument, String> {

    public Optional<CarritoDocument> findByEmail(String email);
}
